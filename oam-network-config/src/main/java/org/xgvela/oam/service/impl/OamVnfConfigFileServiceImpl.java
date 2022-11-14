package org.xgvela.oam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.config.SftpConfig;
import org.xgvela.oam.entity.conf.FileTree;
import org.xgvela.oam.entity.conf.OamVnfConfigFile;
import org.xgvela.oam.entity.conf.OamVnfConfigTask;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.grpc.ConfGrpcClient;
import org.xgvela.oam.mapper.conf.OamVnfConfigFileMapper;
import org.xgvela.oam.mapper.conf.OamVnfConfigTaskMapper;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.service.ConfigUpdateResultService;
import org.xgvela.oam.service.IOamVnfConfigFileService;
import org.xgvela.oam.utils.FileTreeUtils;
import org.xgvela.oam.utils.SftpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
@Slf4j
public class OamVnfConfigFileServiceImpl extends ServiceImpl<OamVnfConfigFileMapper, OamVnfConfigFile> implements IOamVnfConfigFileService {

    @Autowired
    private OamVnfConfigTaskMapper oamVnfConfigTaskMapper;
    @Autowired
    private OamVnfMapper oamVnfMapper;
    @Autowired
    private OamVnfConfigFileMapper oamVnfConfigFileMapper;
    @Autowired
    private ConfGrpcClient confGrpcClient;
    @Resource
    private SftpConfig sftpConfig;

    public final static String CONF_DELIVERY = "conf_delivery";
    public final static String CONF_SWITCH = "conf_switch";

    void init() {
        confGrpcClient = confGrpcClient.getConfGrpcClient();
    }

    @Override
    public List<OamVnfConfigFile> confInfo(String neId, String neType, String vnfName) {
        List<String> vnfIds = oamVnfMapper.selectList(Wrappers.<OamVnf>lambdaQuery().like(OamVnf::getVnfName, vnfName)).stream().map(OamVnf::getNeId).collect(Collectors.toList());
        return baseMapper.selectList(Wrappers.<OamVnfConfigFile>lambdaQuery()
                .like(ObjectUtils.isNotEmpty(neId), OamVnfConfigFile::getNeId, neId)
                .like(ObjectUtils.isNotEmpty(neType), OamVnfConfigFile::getNeType, neType)
                .in(ObjectUtils.isNotEmpty(vnfName), OamVnfConfigFile::getNeId, vnfIds)
        );
    }

    @Override
    public boolean confDelivery(OamVnfConfigFile.VnfConfigDeliveryRequest request) {
        return createTask(request, CONF_DELIVERY);
    }

    @Override
    public boolean confSwitch(OamVnfConfigFile.VnfConfigDeliveryRequest request) {
        return createTask(request, CONF_SWITCH);
    }

    @Override
    public boolean confResult(OamVnfConfigFile.ConfUpdateRequest request) {
        log.info(" notifying the upper-layer NMS of the update result neId:{}, result:{}", request.getNeId(), request.getResult());
        return true;
    }

    @Override
    public boolean notifyDownLoadFiles(OamVnfConfigFile.Vnf request) {
        String neType = request.getNeType();
        String neId = request.getNeId();
        String version = String.valueOf(new Date().getTime());
        String configReadPath = format("/root/sftp/read/conf/%s/%s/", neType, neId);
        String configWritePath = format("/root/sftp/write/conf/%s/%s/", neType, neId);
        String sftpReadPath = format("%s/read/conf/%s/%s/", sftpConfig.getSftpAgentPath(), neType, neId);
        String sftpWritePath = format("%s/write/conf/%s/%s/", sftpConfig.getSftpConfigPath(), neType, neId);
        log.info("notifyDownLoadFiles ,configReadPath {}, configWritePath {},sftpReadPath {}", configReadPath, configWritePath, sftpReadPath);
        log.info("notifyDownLoadFiles: sftpConfigClient download file for config read path");
        SftpUtils sftpAgentClient = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        SftpUtils sftpConfigClient = new SftpUtils(sftpConfig.getSftpConfSeverIp(), Integer.parseInt(sftpConfig.getSftpConfigPort()), sftpConfig.getSftpConfigUser(), sftpConfig.getSftpConfigPasswd());
        FileTreeUtils.collectFileFromSftpToLocalForVersion(configReadPath, sftpReadPath, sftpAgentClient, sftpConfigClient, "readWithoutVersion", null);

        log.info("notifyDownLoadFiles: sftpConfigClient download file for config write path");
        ConfigUpdateResultService.collect(sftpReadPath, configWritePath, version, sftpConfig);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("Exception:: ", e);
        }
        List<FileTree> fileTrees = FileTreeUtils.getLocalDirectory(configReadPath);
        ConfigUpdateResultService.versionFileToDatabase(neId, neType, sftpWritePath, version, configReadPath, fileTrees, oamVnfConfigFileMapper, log);
        return true;
    }

    @Override
    public boolean cfgUpdateResultNotify(OamVnfConfigFile.VnfRequest request) {

        SftpUtils sftp = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        String sftpAgentReadPath = String.format("%s/read/conf/%s/%s/", sftpConfig.getSftpAgentPath(), request.getNeType(), request.getNeId());
        String localWriteDir = String.format("/root/sftp/write/conf/%s/%s/", request.getNeType(), request.getNeId());
        String sftpAgentWritePath = String.format("%s/conf/write/%s/%s/", sftpConfig.getSftpAgentPath(), request.getNeType(), request.getNeId());

        FileTreeUtils.collectFileFromSftpToLocal(localWriteDir, sftpAgentReadPath, sftp);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            log.error("Exception:: ", e);
        }

        List<FileTree> trees = FileTreeUtils.getLocalDirectory(localWriteDir);
        trees.forEach(tree -> {
            String newFileContent;
            String inUseFileContent;
            OamVnfConfigFile oamVnfConfigFile = oamVnfConfigFileMapper.selectOne(Wrappers.<OamVnfConfigFile>lambdaQuery().eq(OamVnfConfigFile::getIsUse, true).like(OamVnfConfigFile::getCfName, tree.getName()));
            List<OamVnfConfigFile> oamVnfConfigFiles = oamVnfConfigFileMapper.selectList(Wrappers.<OamVnfConfigFile>lambdaQuery().eq(OamVnfConfigFile::getIsUse, false).like(OamVnfConfigFile::getCfName, tree.getName()).orderByDesc(OamVnfConfigFile::getCfUpdatetime));
            try {
                OamVnfConfigTask task = oamVnfConfigTaskMapper.selectById(request.getTaskId());

                String fileName = Arrays.asList(tree.getName().split("_")).get(0);
                newFileContent = FileUtils.readFileToString(new File(String.format("%s%s", localWriteDir, fileName)), Charset.defaultCharset());
                inUseFileContent = FileUtils.readFileToString(new File(oamVnfConfigFile.getCfPath()), Charset.defaultCharset());
                if (StringUtils.equals(newFileContent, inUseFileContent)) {

                    task.setCreateTime(new Date());
                    task.setStatus(OamVnfConfigTask.statusType.DONE.getName());
                    oamVnfConfigTaskMapper.updateById(task);
                } else {
                    log.info("agent conf start revert");
                    SftpUtils sftpAgent = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
                    log.info(" conf upload sftp-path:{} ,file: {}", sftpAgentWritePath, oamVnfConfigFile.getCfPath());

                    FileUtils.writeStringToFile(new File(oamVnfConfigFiles.get(0).getCfPath()), oamVnfConfigFiles.get(0).getFileContent());
                    sftpAgent.upload(sftpAgentWritePath, new File(oamVnfConfigFile.getCfPath()));
                    String updateRsp;
                    if (tree.getName().contains("_")) {
                        updateRsp = confGrpcClient.getConfGrpcClient().updateConfigFile(request.getNeType(), request.getNeId(), tree.getName().split("_")[1], String.valueOf(task.getTaskId())).getUpdateRsp();
                    } else {
                        updateRsp = confGrpcClient.getConfGrpcClient().updateConfigFile(request.getNeType(), request.getNeId(), tree.getName(), String.valueOf(task.getTaskId())).getUpdateRsp();
                    }
                    log.info("conf >> agent  updateRsp {}", updateRsp);
                }
            } catch (Exception e) {
                log.error("Exception::", e);
            }
        });
        return true;
    }

    @Override
    public boolean deleteFileAndTask(String id) {
        oamVnfConfigFileMapper.delete(Wrappers.<OamVnfConfigFile>lambdaQuery().eq(OamVnfConfigFile::getNeId, id));
        oamVnfConfigTaskMapper.delete(Wrappers.<OamVnfConfigTask>lambdaQuery().eq(OamVnfConfigTask::getNeId, id));
        return true;
    }

    private boolean createTask(OamVnfConfigFile.VnfConfigDeliveryRequest deliveryRequest, String method) {
        OamVnf vnf = oamVnfMapper.selectById(deliveryRequest.getNeId());
        String neId = vnf.getNeId();
        String neType = vnf.getNeType();
        String fileName = null;

        if (method.equals(CONF_DELIVERY)) {
            fileName = deliveryRequest.getFile().getCfName();
        } else if (method.equals(CONF_SWITCH)) {
            fileName = Optional.ofNullable(oamVnfConfigFileMapper.selectOne(Wrappers.<OamVnfConfigFile>lambdaQuery().eq(OamVnfConfigFile::getCfVersion, deliveryRequest.getFile().getVersion()))).map(OamVnfConfigFile::getCfName).orElse("");
        }

        String sftpWriteAgentPath = String.format("%s/write/conf/%s/%s/", sftpConfig.getSftpAgentPath(), neType, neId);

        oamVnfConfigTaskMapper.insert(OamVnfConfigTask.builder()
                .neId(neId)
                .neType(neType)
                .vnfName(vnf.getVnfName())
                .vnfManageIp(vnf.getVnfManageIp())
                .callbackUrl(deliveryRequest.getCallbackUrl())
                .status(OamVnfConfigTask.statusType.UNDO.getName())
                .createTime(new Date())
                .type(method)
                .version(deliveryRequest.getFile().getVersion())
                .vnfSignalPort(vnf.getVnfManagePort()).build()
        );

        String localtestDir = String.format("/root/sftp/write/conf/%s/%s/", neType, neId);
        log.info("localtestDir  {}", localtestDir);
        log.info("upload file {}", String.format("%s%s", localtestDir, fileName));

        SftpUtils sftp = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        configUploadAgent(String.format("%s%s", localtestDir, fileName), sftp, sftpWriteAgentPath);
        String updateRsp;

        List<OamVnfConfigTask> tasks = oamVnfConfigTaskMapper.selectList(Wrappers.<OamVnfConfigTask>lambdaQuery().eq(OamVnfConfigTask::getNeId, neId).orderByDesc(OamVnfConfigTask::getCreateTime));
        OamVnfConfigTask task = tasks.get(0);
        assert fileName != null;
        if (fileName.contains("_")) {
            log.info("fileName {}", fileName);
            updateRsp = confGrpcClient.getConfGrpcClient().updateConfigFile(neType, neId, fileName.split("_")[1], String.valueOf(task.getTaskId())).getUpdateRsp();
        } else {
            log.info("fileName {}", fileName);
            updateRsp = confGrpcClient.getConfGrpcClient().updateConfigFile(neType, neId, fileName, String.valueOf(task.getTaskId())).getUpdateRsp();
        }
        log.info("conf >> agent  updateRsp {}", updateRsp);

        if (ObjectUtils.isNotEmpty(tasks)) {
            oamVnfConfigTaskMapper.updateById(task);
            log.info("taskid {} status {}", task.getTaskId(), OamVnfConfigTask.statusType.DOING.getName());
        }
        return true;
    }

    public void configUploadAgent(String localWorkingDir, SftpUtils sftp, String sftpWriteConfigPath) {
        File originFile = new File(localWorkingDir);
        try {
            sftp.upload(sftpWriteConfigPath, originFile);
            sftp.close();
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }
}
