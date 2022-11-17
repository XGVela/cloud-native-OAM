package org.xgvela.oam.grpc;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xgvela.oam.nftask.NfTaskReq;
import org.xgvela.oam.nftask.NfTaskResp;
import org.xgvela.oam.nftask.NfTaskServiceGrpc;
import org.xgvela.oam.traceinfo.TraceInfoReq;
import org.xgvela.oam.traceinfo.TraceInfoResp;
import org.xgvela.oam.traceinfo.TraceInfoServiceGrpc;

import java.util.concurrent.TimeUnit;

@Data
@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SignalingGrpcClient {

    private static final int deadlineTime = 1000;
    private ManagedChannel channel;
    private NfTaskServiceGrpc.NfTaskServiceBlockingStub nfTaskServiceBlockingStub;
    private TraceInfoServiceGrpc.TraceInfoServiceBlockingStub traceInfoServiceBlockingStub;

    public SignalingGrpcClient getSignaltraceGrpcClient(String host, Integer port) {
        this.shutdown();
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        return this;
    }

    public NfTaskResp createNfTask(NfTaskReq nfTaskReq) {
        this.setNfTaskServiceBlockingStub(NfTaskServiceGrpc.newBlockingStub(this.channel));
        return this.getNfTaskServiceBlockingStub().withDeadlineAfter(deadlineTime, TimeUnit.SECONDS).createNfTask(nfTaskReq);
    }

    public TraceInfoResp informTraceInfo(TraceInfoReq traceInfoReq) {
        this.setTraceInfoServiceBlockingStub(TraceInfoServiceGrpc.newBlockingStub(this.channel));
        return this.getTraceInfoServiceBlockingStub().withDeadlineAfter(deadlineTime, TimeUnit.SECONDS).informTraceInfo(traceInfoReq);
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
