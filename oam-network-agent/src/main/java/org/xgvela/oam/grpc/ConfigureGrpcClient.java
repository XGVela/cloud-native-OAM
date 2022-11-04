package org.xgvela.oam.grpc;

import com.google.protobuf.ByteString;
import org.xgvela.oam.configupdateresult.CfgResultNotifyReq;
import org.xgvela.oam.configupdateresult.CfgResultNotifyResp;
import org.xgvela.oam.configupdateresult.ConfigUpdateResultServiceGrpc;
import org.xgvela.oam.configuremanagerwithfile.ConfigureManagerWithFileServiceGrpc;
import org.xgvela.oam.configuremanagerwithfile.UpdateCfgFileWithFileReq;
import org.xgvela.oam.configuremanagerwithfile.UpdateCfgWithFileRsp;
import org.xgvela.oam.entity.UpdateFileCallBack;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Data
@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ConfigureGrpcClient {

    private static final int deadlineTime = 50;
    private ManagedChannel channel;
    /**
     * oam-network-agent -> grpc -> oam-network-simulator
     */
    private ConfigureManagerWithFileServiceGrpc.ConfigureManagerWithFileServiceStub configureManagerWithFileServiceStub;
    private ConfigUpdateResultServiceGrpc.ConfigUpdateResultServiceBlockingStub configUpdateResultServiceBlockingStub;

    public ConfigureGrpcClient getConfigureGrpcClient(String nfHost, Integer nfPort) {
        this.shutdown();
        this.channel = ManagedChannelBuilder.forAddress(nfHost, nfPort).usePlaintext().build();
        return this;
    }

    public void updateConfigFileBusService(UpdateFileCallBack updateFileCallBack, String nfType, String neId, String taskId, String fileName, ByteString fileData){
        this.setConfigureManagerWithFileServiceStub(ConfigureManagerWithFileServiceGrpc.newStub(this.channel));
        UpdateCfgFileWithFileReq updateCfgFileWithFileReq = UpdateCfgFileWithFileReq.newBuilder().setNfType(nfType).setNeId(neId).setTaskId(taskId).setFileName(fileName).setFileData(fileData).build();
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<UpdateCfgWithFileRsp> updateCfgFileRspStreamObserver = new StreamObserver<UpdateCfgWithFileRsp>() {
            @Override
            public void onNext(UpdateCfgWithFileRsp value) {
                log.info("UpdateCfgWithFileRsp Result : " + value.getUpdateRsp());
                updateFileCallBack.setResult(value.getUpdateRsp());
            }

            @Override
            public void onError(Throwable t) {
                log.info("UpdateCfgWithFileRsp Occurs Exception: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                log.info("Update Config File Completed");
                finishLatch.countDown();
            }
        };
        StreamObserver<UpdateCfgFileWithFileReq> updateCfgFileReqStreamObserver = this.getConfigureManagerWithFileServiceStub().updateConfigWithFile(updateCfgFileRspStreamObserver);
        updateCfgFileReqStreamObserver.onNext(updateCfgFileWithFileReq);
        try{
            if (finishLatch.getCount() == 0) {
                log.info("RPC completed or errored before we finished sending.");
                return;
            }
        }catch (Exception interruptedException){
            updateCfgFileReqStreamObserver.onError(interruptedException);
        }
        updateCfgFileReqStreamObserver.onCompleted();
        try {
            if (!finishLatch.await(20, TimeUnit.SECONDS)) {
                log.info("FAILED : Process updateCfgFile cannot finish within 20 seconds");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CfgResultNotifyResp cfgUpdateResult(String nfType, String instanceId, String taskId){
        this.setConfigUpdateResultServiceBlockingStub(ConfigUpdateResultServiceGrpc.newBlockingStub(this.channel));
        CfgResultNotifyReq cfgResultNotifyReq = CfgResultNotifyReq.newBuilder().setNfType(nfType).setInstanceId(instanceId).setTaskId(taskId).build();
        return this.getConfigUpdateResultServiceBlockingStub().withDeadlineAfter(deadlineTime, TimeUnit.SECONDS).cfgUpdateResult(cfgResultNotifyReq);
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
