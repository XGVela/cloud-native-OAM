package org.xgvela.oam.service.signal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.xgvela.oam.grpc.SignalingGrpcClient;
import org.xgvela.oam.traceinfo.TraceInfoReq;
import org.xgvela.oam.traceinfo.TraceInfoResp;
import org.xgvela.oam.traceinfo.TraceInfoServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Component;


/**
 * Grpc Server
 */
@Component
@GrpcService
@Slf4j
public class SignalingInformService extends TraceInfoServiceGrpc.TraceInfoServiceImplBase {

    @Autowired
    private SignalingGrpcClient signalingGrpcClient;

    @Value("${signaling.grpc.server}")
    private String siginalGrpcServer;
    @Value("${signaling.grpc.port}")
    private String siginalGrpcPort;

    @Override
    public void informTraceInfo(TraceInfoReq request, StreamObserver<TraceInfoResp> responseObserver) {
        log.info("Agent TraceInfo Req : {}{}", request.getTaskId(), request.getData());
        TraceInfoResp traceInfoResp = signalingGrpcClient.getSignaltraceGrpcClient(siginalGrpcServer, Integer.valueOf(siginalGrpcPort)).informTraceInfo(request).getResult().equals("0") ? TraceInfoResp.newBuilder().setResult("0").build() : TraceInfoResp.newBuilder().setResult("1").build();
        responseObserver.onNext(traceInfoResp);
        responseObserver.onCompleted();
    }
}
