package org.xgvela.oam.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import org.springframework.stereotype.Component;
import org.xgvela.oam.entity.signaltrace.OamVnfSignaltrace;
import org.xgvela.oam.mapper.signaltrace.OamVnfSignaltraceMapper;
import org.xgvela.oam.traceinfo.TraceInfoReq;
import org.xgvela.oam.traceinfo.TraceInfoResp;
import org.xgvela.oam.traceinfo.TraceInfoServiceGrpc;
import org.xgvela.oam.utils.HttpPostClient;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Grpc Server
 */
@Component
@GrpcService
@Slf4j
public class SignalingInformService extends TraceInfoServiceGrpc.TraceInfoServiceImplBase {

    @Resource
    private HttpPostClient httpPostClient;
    @Resource
    private OamVnfSignaltraceMapper signaltraceMapper;

    @Override
    public void informTraceInfo(TraceInfoReq request, StreamObserver<TraceInfoResp> responseObserver) {
        log.info("Agent TraceInfo Req : {}{}", request.getTaskId(), request.getData());
        String SUCCEED = "0";
        TraceInfoResp traceInfoResp = TraceInfoResp.newBuilder().setResult(SUCCEED).build();
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", request.getTaskId());
        map.put("data", request.getData());
        OamVnfSignaltrace signaltrace = signaltraceMapper.selectById(request.getTaskId());

        responseObserver.onNext(traceInfoResp);
        responseObserver.onCompleted();
        signaltrace.setStatus(OamVnfSignaltrace.statusEnum.DONE.getName());
        signaltraceMapper.updateById(signaltrace);
        log.info("signaltrace to upper oss Req");
        httpPostClient.send(signaltrace.getCallbackUrl(), map);
    }
}
