package org.xgvela.oam.service.signal;

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
    @Override
    public void informTraceInfo(TraceInfoReq request, StreamObserver<TraceInfoResp> responseObserver) {

        log.info("Agent TraceInfo Req : {}{}", request.getTaskId(), request.getData());
         TraceInfoResp traceInfoResp = TraceInfoResp.newBuilder().setResult("0").build();
         responseObserver.onNext(traceInfoResp);
         responseObserver.onCompleted();
    }
}
