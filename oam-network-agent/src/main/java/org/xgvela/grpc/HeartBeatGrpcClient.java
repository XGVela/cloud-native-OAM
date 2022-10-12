package org.xgvela.grpc;

import org.xgvela.config.HeartBeatConfiguration;
import org.xgvela.oam.heartbeat.HeartBeatReportServiceGrpc;
import org.xgvela.oam.heartbeat.HeartReq;
import org.xgvela.oam.heartbeat.HeartRsp;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Data
@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class HeartBeatGrpcClient {
    @Resource
    private HeartBeatConfiguration heartBeatConfiguration;
    private static final int deadlineTime = 5;
    private ManagedChannel channel;
    private HeartBeatReportServiceGrpc.HeartBeatReportServiceBlockingStub heartBeatReportServiceBlockingStub;

    public HeartBeatGrpcClient getHeartBeatGrpcClient() {
        this.shutdown();
        this.channel = ManagedChannelBuilder.forAddress(heartBeatConfiguration.getHost(), heartBeatConfiguration.getPort()).usePlaintext().build();
        return this;
    }

    public HeartRsp heartBeatReport(String neType, String neId) {
        this.setHeartBeatReportServiceBlockingStub(HeartBeatReportServiceGrpc.newBlockingStub(this.channel));
        HeartReq heartReq = HeartReq.newBuilder().setNeType(neType).setNeId(neId).build();
        return this.getHeartBeatReportServiceBlockingStub().withDeadlineAfter(deadlineTime, TimeUnit.SECONDS).heartBeatReport(heartReq);
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
