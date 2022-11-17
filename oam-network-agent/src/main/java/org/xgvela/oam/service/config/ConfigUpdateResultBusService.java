package org.xgvela.oam.service.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.protobuf.ByteString;
import org.xgvela.oam.config.SftpConfig;
import org.xgvela.oam.configupdateresult.CfgResultNotifyReq;
import org.xgvela.oam.configupdateresult.CfgResultNotifyResp;
import org.xgvela.oam.configupdateresult.ConfigUpdateResultServiceGrpc;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.grpc.ConfigureGrpcClient;
import org.xgvela.oam.grpc.SyncConfigGrpcClient;
import org.xgvela.oam.service.heartbeat.IOamVnfSelectService;
import org.xgvela.oam.syncconfig.GetFileResp;
import org.xgvela.oam.utils.SftpUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;

@Component
@Slf4j
@GrpcService
//oam-network-simulator -> oam-network-agent -> oam-network-config
public class ConfigUpdateResultBusService extends ConfigUpdateResultServiceGrpc.ConfigUpdateResultServiceImplBase {

    private static final String NfConfFileName = "upf.xml";
    private static final String GetFileRespSucceed = "0";
    //    /sftp-agent/sftp/read/UPF/upfinstanceid001
//    D:\test\read
//    private static final String ReadPath = "/sftp-config/read"; //data/conf/read/AMF/amfinstanceid002/text.xml
    private static final String ReadPath = "/root/sftp/read/conf"; //data/conf/read/AMF/amfinstanceid002/text.xml
    private static final String UpdateConfigFileSucceed = "0";
    private static final String UpdateConfigFileFailed = "1";

    @Value("${config.grpc.server}")
    private String configGrpcServer;
    @Value("${config.grpc.port}")
    private String configGrpcPort;
    @Value("${configServerhttpIp}")
    private String configServerIp;
    @Value("${configServerhttpPort}")
    private String configServerhttpPort;
    private static final String simulator_server = "simulator.inspur-xgvela1-infra-upf-";

    @Autowired
    private SftpConfig sftpConfig;
    @Autowired
    private SyncConfigGrpcClient syncConfigGrpcClient;
    @Autowired
    private IOamVnfSelectService iOamVnfSelectService;
    @Autowired
    private ConfigureGrpcClient configureGrpcClient;

    @Override
    public void cfgUpdateResult(CfgResultNotifyReq cfgResultNotifyReq, StreamObserver<CfgResultNotifyResp> responseObserver) {
        log.info("Accepted CfgResultNotifyReq request: " + cfgResultNotifyReq.getNfType() + "," + cfgResultNotifyReq.getInstanceId() + "," + cfgResultNotifyReq.getTaskId());

        LambdaQueryWrapper<OamVnf> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamVnf::getNeType, cfgResultNotifyReq.getNfType());
        lambdaQueryWrapper.eq(OamVnf::getNeId, cfgResultNotifyReq.getInstanceId());
        OamVnf oamVnf = iOamVnfSelectService.getOne(lambdaQueryWrapper);

        log.info("cfgUpdateResult >> config ,ip {}, port{}", oamVnf.getVnfManageIp(), Integer.parseInt(oamVnf.getVnfSignalPort()));

        syncConfigGrpcClient = syncConfigGrpcClient.getSyncConfigGrpcClient(oamVnf.getVnfManageIp(), Integer.parseInt(oamVnf.getVnfSignalPort()));

        if (oamVnf.getVnfManageIp().contains("agent")) {
            log.info("ip vnfSignalPort netype neid : {} , {},{} , {}", simulator_server + cfgResultNotifyReq.getInstanceId(), Integer.valueOf(oamVnf.getVnfSignalPort()), cfgResultNotifyReq.getNfType(), cfgResultNotifyReq.getInstanceId());
            syncConfigGrpcClient = syncConfigGrpcClient.getSyncConfigGrpcClient(simulator_server + cfgResultNotifyReq.getInstanceId(), Integer.parseInt(oamVnf.getVnfSignalPort()));
        } else {
            log.info("ip vnfSignalPort netype neid : {} , {},{} , {}", oamVnf.getVnfManageIp(),  Integer.valueOf(oamVnf.getVnfSignalPort()), cfgResultNotifyReq.getNfType(), cfgResultNotifyReq.getInstanceId());
            syncConfigGrpcClient = syncConfigGrpcClient.getSyncConfigGrpcClient(oamVnf.getVnfManageIp(), Integer.parseInt(oamVnf.getVnfSignalPort()));
        }

        Iterator<GetFileResp> iterator = syncConfigGrpcClient.getConfigFile(cfgResultNotifyReq.getNfType(), cfgResultNotifyReq.getInstanceId(), NfConfFileName);
        log.info("syncConfigGrpcClient.getConfigFile success !!");

        String filePathFile = String.format("%s/UPF/%s/%s", ReadPath, cfgResultNotifyReq.getInstanceId(), NfConfFileName);
        String filePath = String.format("%s/UPF/%s/%s", ReadPath, cfgResultNotifyReq.getInstanceId(), NfConfFileName);
        String sftpFilePath = filePath.replaceAll("/root/sftp/", "/sftp-agent/sftp/");

        log.info("cfgUpdateResult sftp: ip{} port{} user{} password{}", sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
        SftpUtils sftpAgentClient = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());

        File file = new File(filePathFile);
        try {
            sftpAgentClient.mkdir(sftpFilePath);
            Thread.sleep(5000);
        } catch (Exception e) {
            log.info("Exception::", e);
        }
        while (iterator.hasNext()) {
            GetFileResp getFileResp = iterator.next();
            if (GetFileRespSucceed.equals(getFileResp.getResult())) {
                ByteString bytes = getFileResp.getFileData();
                byte[] byteArray = bytes.toByteArray();
                String content = new String(byteArray);
                try {
                    FileUtils.write(file, content, "UTF-8");
                } catch (Exception e) {
                    log.info("Update Config Write NfConfigFile to ReadPath: " + filePath);
                }
            } else {
                log.info("Update Config GetConfigFile Result: " + getFileResp.getResult());
            }
        }
        log.info("configGrpcServer: {} ,configGrpcPort: {}", configGrpcServer, configGrpcPort);

        configureGrpcClient = configureGrpcClient.getConfigureGrpcClient(configGrpcServer, Integer.valueOf(configGrpcPort)); //此处配置oam-network-config服务grpc server信息

        CfgResultNotifyResp cfgResultNotifyResp = configureGrpcClient.cfgUpdateResult(cfgResultNotifyReq.getNfType(), cfgResultNotifyReq.getInstanceId(), cfgResultNotifyReq.getTaskId());
        log.info("Agent Inform Config Service CfgUpdate Result: " + cfgResultNotifyResp);
        if (UpdateConfigFileSucceed.equals(cfgResultNotifyResp.getResult())) {
            log.info("CfgUpdate Succeed");
        }
        responseObserver.onNext(cfgResultNotifyResp);
        responseObserver.onCompleted();
    }
}
