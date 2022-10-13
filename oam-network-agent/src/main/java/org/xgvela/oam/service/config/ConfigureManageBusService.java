package org.xgvela.oam.service.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.protobuf.ByteString;
import org.xgvela.oam.config.SftpConfig;
import org.xgvela.oam.configuremanager.ConfigureManagerServiceGrpc;
import org.xgvela.oam.configuremanager.UpdateCfgFileReq;
import org.xgvela.oam.configuremanager.UpdateCfgFileRsp;
import org.xgvela.oam.entity.UpdateFileCallBack;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.grpc.ConfigureGrpcClient;
import org.xgvela.oam.service.heartbeat.IOamVnfSelectService;
import org.xgvela.oam.utils.SftpUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
@GrpcService
/**
 *  oam-network-config -> grpc(ConfigureManagerService ) -> oam-network-agent 通知(不带文件流)
 */
public class ConfigureManageBusService extends ConfigureManagerServiceGrpc.ConfigureManagerServiceImplBase {

    ////    /sftp-agent/sftp/read/UPF/upfinstanceid001
    ///root/sftp/read/UPF/upfinstanceid001
    ////   D:\test\write
    private static final String WritePath = "/root/sftp/write";
    private static final String UpdateCfgWithFileSucceed = "0";

    @Autowired
    private ConfigureGrpcClient configureGrpcClient;
    @Autowired
    private IOamVnfSelectService iOamVnfSelectService;
    @Autowired
    private SftpConfig sftpConfig;

    @Override
    public void updateConfigFile(UpdateCfgFileReq updateCfgFileReq, StreamObserver<UpdateCfgFileRsp> responseObserver) {
        log.info("Accepted UpdateCfgFileReq request: " + updateCfgFileReq.getNfType() + "," + updateCfgFileReq.getNeId() + "," + updateCfgFileReq.getTaskId() + "," + updateCfgFileReq.getFileName());
        LambdaQueryWrapper<OamVnf> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamVnf::getNeType, updateCfgFileReq.getNfType());
        lambdaQueryWrapper.eq(OamVnf::getNeId, updateCfgFileReq.getNeId());
        OamVnf oamVnf = iOamVnfSelectService.getOne(lambdaQueryWrapper);
        log.info("VnfManageIp {},VnfSignalPort {}", oamVnf.getVnfManageIp(), Integer.valueOf(oamVnf.getVnfSignalPort()));
        configureGrpcClient = configureGrpcClient.getConfigureGrpcClient(oamVnf.getVnfManageIp(), Integer.valueOf(oamVnf.getVnfSignalPort()));

//        String writeConfPath = WritePath + "\\" + updateCfgFileReq.getFileName();
        String writeConfPath = String.format("%s/UPF/%s/", WritePath, updateCfgFileReq.getNeId());
        String writeConfPathFile = String.format("%s/UPF/%s/%s", WritePath, updateCfgFileReq.getNeId(), updateCfgFileReq.getFileName());
//        String filePathWindows = ReadPath + "/UPF/" + neId + "/" + NfConfFileName;
        SftpUtils sftpAgentClient = new SftpUtils(sftpConfig.getSftpAgentPath(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        ByteString bytes = getConfigFileBytes(writeConfPath, writeConfPathFile, sftpAgentClient);
        UpdateFileCallBack updateFileCallBack = new UpdateFileCallBack();
        configureGrpcClient.updateConfigFileBusService(updateFileCallBack, updateCfgFileReq.getNfType(), updateCfgFileReq.getNeId(), updateCfgFileReq.getTaskId(), updateCfgFileReq.getFileName(), bytes);
        String result = "1";
        if (UpdateCfgWithFileSucceed.equals(updateFileCallBack.getResult())) {
            result = "0";
        }
        UpdateCfgFileRsp updateCfgFileRsp = UpdateCfgFileRsp.newBuilder().setUpdateRsp(result).build();
        responseObserver.onNext(updateCfgFileRsp);
        responseObserver.onCompleted();
    }

    private static ByteString getConfigFileBytes(String path, String filePath, SftpUtils sftpAgentClient) {
        try {
            String sftpFilePath=path.replaceAll("/root/sftp/","/sftp-agent/sftp/");
            try {
                sftpAgentClient.mkdir(sftpFilePath);
                Thread.sleep(5000);
            } catch (Exception e) {
                log.info("Exception::",e);
            }
            String fileContent = FileUtils.readFileToString(new File(filePath), "UTF-8");
            ByteString bytes = ByteString.copyFromUtf8(fileContent);
            return bytes;
        } catch (IOException ioException) {
            log.info("Read ConfigFile Occurs Exception: " + ioException.getMessage());
        }
        return null;
    }
}
