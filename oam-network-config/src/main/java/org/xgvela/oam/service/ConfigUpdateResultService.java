package org.xgvela.oam.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.xgvela.oam.config.SftpConfig;
import org.xgvela.oam.configupdateresult.CfgResultNotifyReq;
import org.xgvela.oam.configupdateresult.CfgResultNotifyResp;
import org.xgvela.oam.configupdateresult.ConfigUpdateResultServiceGrpc;

import org.xgvela.oam.entity.conf.FileTree;
import org.xgvela.oam.entity.conf.OamVnfConfigFile;
import org.xgvela.oam.entity.conf.OamVnfConfigTask;
import org.xgvela.oam.mapper.conf.OamVnfConfigFileMapper;
import org.xgvela.oam.mapper.conf.OamVnfConfigTaskMapper;
import org.xgvela.oam.utils.FileTreeUtils;
import org.xgvela.oam.utils.SftpUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@GrpcService
@Slf4j
public class ConfigUpdateResultService extends ConfigUpdateResultServiceGrpc.ConfigUpdateResultServiceImplBase {
    @Resource
    private SftpConfig sftpConfig;
    @Autowired
    private OamVnfConfigFileMapper vnfConfigFileMapper;
    @Autowired
    private OamVnfConfigTaskMapper vnfConfigTaskMapper;

    @Override
    public void cfgUpdateResult(CfgResultNotifyReq request, StreamObserver<CfgResultNotifyResp> responseObserver) {
        String neId = request.getInstanceId();
        String neType = request.getNfType();
        String taskId = request.getTaskId();
        String fileName = "upf.xml";
        log.info("config accepted  cfgUpdateResult instanceId {} nfType {} taskId {} ", neId, neType, taskId);

        CfgResultNotifyResp cfgResultNotifyResp = CfgResultNotifyResp.newBuilder().setResult("0").build();
        responseObserver.onNext(cfgResultNotifyResp);
        responseObserver.onCompleted();

        SftpUtils sftp = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());

        String sftpAgentReadPath = String.format("%s/read/%s/%s/", sftpConfig.getSftpAgentPath(), request.getNfType(), request.getInstanceId());
        String localConfigWriteDir = String.format("/root/sftp/write/%s/%s", request.getNfType(), request.getInstanceId());
        String localConfigReadDir = String.format("/root/sftp/read/%s/%s", request.getNfType(), request.getInstanceId());
        String sftpAgentWritePath = String.format("%s/write/%s/%s/", sftpConfig.getSftpAgentPath(), request.getNfType(), request.getInstanceId());

        String sftpWritePath = format("%s/write/%s/%s/", sftpConfig.getSftpConfigPath(), neType, neId);
        String version = String.valueOf(new Date().getTime());
        String configReadPath = format("/root/sftp/read/%s/%s/", neType, neId);

        log.info("sftpAgentReadPath: {},localConfigWriteDir: {},localConfigReadDir: {},sftpAgentWritePath: {}", sftpAgentReadPath, localConfigWriteDir, localConfigReadDir, sftpAgentWritePath);

        log.info("cfgUpdateResult :sftpConfigClient download file for config read path");
        SftpUtils sftpAgentClient = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        SftpUtils sftpConfigClient = new SftpUtils(sftpConfig.getSftpConfSeverIp(), Integer.parseInt(sftpConfig.getSftpConfigPort()), sftpConfig.getSftpConfigUser(), sftpConfig.getSftpConfigPasswd());
        FileTreeUtils.collectFileFromSftpToLocalForVersion(localConfigReadDir, sftpAgentReadPath, sftpAgentClient, sftpConfigClient, "", "");

        log.info("cfgUpdateResult :sftpConfigClient download file for config write path");
        collect(sftpAgentReadPath, localConfigWriteDir, version, sftpConfig);


        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            log.error("Exception:: ", e);
        }

        List<FileTree> fileTrees = FileTreeUtils.getLocalDirectory(localConfigReadDir);
        versionFileToDatabase(neId, neType, sftpWritePath, version, configReadPath, fileTrees, vnfConfigFileMapper, log);

        String newFileContent = "";
        String inUseFileContent = "";

        String configReadPathFile = String.format("/root/sftp/read/%s/%s/%s", request.getNfType(), request.getInstanceId(), fileName);
        String configWritePathFile = String.format("/root/sftp/write/%s/%s/%s", request.getNfType(), request.getInstanceId(), fileName);

        try {
            log.info("inUseFileContent {}", configReadPathFile);
            log.info("configWritePath {}", configWritePathFile);

            inUseFileContent = FileUtils.readFileToString(new File(configReadPathFile), Charset.defaultCharset());
            newFileContent = FileUtils.readFileToString(new File(configWritePathFile), Charset.defaultCharset());

            log.info("start compare file ......");
            if (StringUtils.equals(newFileContent, inUseFileContent)) {
                log.info("file content is the same ");
                OamVnfConfigTask task = vnfConfigTaskMapper.selectById(taskId);
                task.setCreateTime(new Date());
                task.setStatus(OamVnfConfigTask.statusType.DONE.getName());
                vnfConfigTaskMapper.updateById(task);
                log.info(" taskId {} , status {} ", taskId, OamVnfConfigTask.statusType.DONE.getName());

            } else {
                log.info("file content is different ,start upload file to agent");
                SftpUtils sftpAgent = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
                log.info("upload  {}", configReadPathFile);
                sftpAgent.upload(sftpAgentWritePath, new File(configReadPathFile));
            }
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }

    public static void collect(String sftpAgentReadPath, String localConfigWriteDir, String version, SftpConfig sftpConfig) {
        SftpUtils sftpAgentClient = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        SftpUtils sftpConfigClient = new SftpUtils(sftpConfig.getSftpConfSeverIp(), Integer.parseInt(sftpConfig.getSftpConfigPort()), sftpConfig.getSftpConfigUser(), sftpConfig.getSftpConfigPasswd());
        FileTreeUtils.collectFileFromSftpToLocalForVersion(localConfigWriteDir, sftpAgentReadPath, sftpAgentClient, sftpConfigClient, "writeWithVersion", version);
    }

    public static void versionFileToDatabase(String neId, String neType, String sftpWritePath, String version, String configReadPath, List<FileTree> fileTrees, OamVnfConfigFileMapper vnfConfigFileMapper, Logger log) {
        try {
            fileTrees.forEach(tree -> {
                try {
                    vnfConfigFileMapper.insert(OamVnfConfigFile.builder()
                            .neId(neId)
                            .neType(neType)
                            .cfPath(sftpWritePath + version + "_" + tree.getName())
                            .fileContent(FileUtils.readFileToString(new File(configReadPath + tree.getName()), Charset.defaultCharset()))
                            .cfName(tree.getName())
                            .cfSize(tree.getSize())
                            .cfUpdatetime(new Date())
                            .cfVersion(version)
                            .isUse(true).build());
                } catch (IOException e) {
                    log.error("IOException ::", e);
                }
                List<OamVnfConfigFile> oamVnfConfigFiles = vnfConfigFileMapper.selectList(
                        Wrappers.<OamVnfConfigFile>lambdaQuery()
                                .eq(OamVnfConfigFile::getNeId, neId)
                                .eq(OamVnfConfigFile::getNeType, neType)
                                .orderByDesc(OamVnfConfigFile::getCfUpdatetime)
                );
                int size = oamVnfConfigFiles.size();
                if (size > 1) {
                    oamVnfConfigFiles.subList(1, size).forEach(item -> {
                        item.setIsUse(false);
                        vnfConfigFileMapper.updateById(item);
                    });
                }
                if (size > 5) {
                    List<OamVnfConfigFile> historyFiles = oamVnfConfigFiles.subList(5, oamVnfConfigFiles.size());
                    vnfConfigFileMapper.deleteBatchIds(historyFiles.stream().map(OamVnfConfigFile::getId).collect(Collectors.toList()));
                }
            });
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }
}
