package org.xgvela.oam.grpc;//package org.xgvela.oam.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xgvela.oam.config.ChannelSingleton;
import org.xgvela.oam.config.ConfigConfiguration;
import org.xgvela.oam.entity.signaltrace.OamVnfSignaltrace;
import org.xgvela.oam.heartbeat.HeartBeatReportServiceGrpc;
import org.xgvela.oam.heartbeat.HeartReq;
import org.xgvela.oam.heartbeat.HeartRsp;
import org.xgvela.oam.nftask.NfTaskReq;
import org.xgvela.oam.nftask.NfTaskResp;
import org.xgvela.oam.nftask.NfTaskServiceGrpc;
import org.xgvela.oam.traceinfo.TraceInfoReq;
import org.xgvela.oam.traceinfo.TraceInfoResp;
import org.xgvela.oam.traceinfo.TraceInfoServiceGrpc;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static io.grpc.stub.ClientCalls.blockingUnaryCall;

@Data
@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SignaltraceGrpcClient {
    @Resource
    private ConfigConfiguration configConfiguration;
    private static final int deadlineTime = 1000;
    private ManagedChannel channel;
    private NfTaskServiceGrpc.NfTaskServiceBlockingStub nfTaskServiceBlockingStub;
    private TraceInfoServiceGrpc.TraceInfoServiceBlockingStub traceInfoServiceBlockingStub;

    public SignaltraceGrpcClient getSignaltraceGrpcClient() {
        this.channel = ManagedChannelBuilder.forAddress(configConfiguration.getAgentHost(), configConfiguration.getAgentPort()).usePlaintext().build();
        return this;
    }

    public NfTaskResp createNfTask(OamVnfSignaltrace source) {
        this.setNfTaskServiceBlockingStub(NfTaskServiceGrpc.newBlockingStub(this.channel));
        NfTaskReq.Builder nfTaskReqBuilder = NfTaskReq.newBuilder()
                .setTaskId(source.getTaskId())
                .setNfType(source.getVnfType())
                .setNeId(source.getVnfInstanceId())
                .setDataType(source.getDataType())
                .setUserNumber(source.getUserNumber())
                .setInterfaceType(source.getInterfaceType());
        if (BooleanUtils.isTrue(source.getRunNow())) {
            nfTaskReqBuilder
                    .setTimeLength(Double.parseDouble(source.getTimeLength()))
                    .setRunNow(source.getRunNow());
        } else {
            nfTaskReqBuilder
                    .setStartTime(source.getStartTime().getTime())
                    .setEndTime(source.getEndTime().getTime())
                    .setRunNow(source.getRunNow());
        }
        return this.getNfTaskServiceBlockingStub().withDeadlineAfter(deadlineTime, TimeUnit.SECONDS).createNfTask(nfTaskReqBuilder.build());
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
