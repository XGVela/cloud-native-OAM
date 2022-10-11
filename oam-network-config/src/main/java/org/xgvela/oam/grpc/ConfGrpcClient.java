package org.xgvela.oam.grpc;

import org.xgvela.oam.config.ConfigConfiguration;
import org.xgvela.oam.configupdateresult.ConfigUpdateResultServiceGrpc;
import org.xgvela.oam.configuremanager.ConfigureManagerServiceGrpc;
import org.xgvela.oam.configuremanager.UpdateCfgFileReq;
import org.xgvela.oam.configuremanager.UpdateCfgFileRsp;
import org.xgvela.oam.syncconfig.SyncConfigServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

//import com.inspur.oam.configuremanager.UpdateCfgFileRsps;

@Data
@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ConfGrpcClient {
    @Resource
    private ConfigConfiguration configConfiguration;
    private static final int deadlineTime = 20;
    private ManagedChannel channel;
    private ConfigUpdateResultServiceGrpc.ConfigUpdateResultServiceBlockingStub configUpdateResultServiceBlockingStub;
    private ConfigureManagerServiceGrpc.ConfigureManagerServiceBlockingStub configureManagerServiceBlockingStub;
    private SyncConfigServiceGrpc.SyncConfigServiceBlockingStub syncConfigServiceBlockingStub;

    public ConfGrpcClient getConfGrpcClient() {
        this.shutdown();
        this.channel = ManagedChannelBuilder.forAddress(configConfiguration.getAgentHost(), configConfiguration.getAgentPort()).usePlaintext().build();
        return this;
    }

    public UpdateCfgFileRsp updateConfigFile(String nfType, String vnfInstanceId, String fileName,String taskId) {
        this.setConfigureManagerServiceBlockingStub(ConfigureManagerServiceGrpc.newBlockingStub(this.channel));
        UpdateCfgFileReq cfgResultNotifyReq = UpdateCfgFileReq.newBuilder().setNfType(nfType).setNeId(vnfInstanceId).setFileName(fileName).setTaskId(taskId).build();
        return this.getConfigureManagerServiceBlockingStub().withDeadlineAfter(deadlineTime, TimeUnit.SECONDS).updateConfigFile(cfgResultNotifyReq);
    }

    public void shutdown() {
        if (this.channel != null) {
            boolean hasTerminated = this.channel.isTerminated();
            if (hasTerminated) {
                log.info("Grpc Channel has Terminated");
                this.channel.shutdownNow();
            } else {
                this.channel.shutdown();
            }
        }
    }
}
