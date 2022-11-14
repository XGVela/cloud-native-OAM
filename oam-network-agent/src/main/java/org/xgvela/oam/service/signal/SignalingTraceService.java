package org.xgvela.oam.service.signal;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.grpc.SignalingGrpcClient;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.nftask.NfTaskReq;
import org.xgvela.oam.nftask.NfTaskResp;
import org.xgvela.oam.nftask.NfTaskServiceGrpc;

import javax.annotation.Resource;
import java.util.Optional;


@Component
@Slf4j
@GrpcService
public class SignalingTraceService extends NfTaskServiceGrpc.NfTaskServiceImplBase {
    @Autowired
    private SignalingGrpcClient signalingGrpcClient;
    @Resource
    private OamVnfMapper oamVnfMapper;
    private final static String SUCCEED = "0";
    private final static String FAILED = "1";
    private static final String simulator_server = "simulator.inspur-xgvela1-infra-upf-";

    @Override
    public void createNfTask(NfTaskReq request, StreamObserver<NfTaskResp> responseObserver) {
        log.info("Agent NfTaskReq Req :taskId [{}] ,interfaceType [{}], userNumber [{}], runNow [{}]", request.getTaskId(), request.getInterfaceType(), request.getUserNumber(), request.getRunNow());
        OamVnf oamVnf = oamVnfMapper.selectById(request.getNeId());

        if (oamVnf.getVnfManageIp().contains("agent")) {
            log.info("simulator vnfSignalPort nfType neId:[{}],[{}],[{}],[{}]", simulator_server + request.getNeId(), Integer.valueOf(oamVnf.getVnfSignalPort()), request.getNfType(), request.getNeId());
            signalingGrpcClient = signalingGrpcClient.getSignaltraceGrpcClient(simulator_server + request.getNeId(), Integer.valueOf(oamVnf.getVnfSignalPort()));
        } else {
            log.info("simulator vnfSignalPort nfType neId:[{}],[{}],[{}],[{}]", oamVnf.getVnfManageIp(), Integer.valueOf(oamVnf.getVnfSignalPort()), request.getNfType(), request.getNeId());
            signalingGrpcClient = signalingGrpcClient.getSignaltraceGrpcClient(oamVnf.getVnfManageIp(), Integer.valueOf(oamVnf.getVnfSignalPort()));
        }

        NfTaskResp agentToSimulator = signalingGrpcClient.createNfTask(request);

        Optional.ofNullable(agentToSimulator).orElseThrow((() -> new ServiceException("signal to agent createNfTask error:")));
        agentToSimulator = agentToSimulator.getResult().equals(SUCCEED) ? NfTaskResp.newBuilder().setResult(SUCCEED).build() : NfTaskResp.newBuilder().setResult(FAILED).build();

        responseObserver.onNext(agentToSimulator);
        responseObserver.onCompleted();
    }
}
