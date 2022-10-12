package org.xgvela.service.heartbeat;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.grpc.HeartBeatGrpcClient;
import org.xgvela.oam.heartbeat.HeartBeatReportServiceGrpc;
import org.xgvela.oam.heartbeat.HeartReq;
import org.xgvela.oam.heartbeat.HeartRsp;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Grpc Server
 */
@Component
@Slf4j
@GrpcService
public class  HeartBeatService extends HeartBeatReportServiceGrpc.HeartBeatReportServiceImplBase {

    private static final String VnfManagedStatus = "1";

    @Autowired
    private HeartBeatGrpcClient heartBeatGrpcClient;
    @Autowired
    private IOamVnfSelectService iOamVnfSelectService;

    @Override
    public void heartBeatReport(HeartReq heartReq, StreamObserver<HeartRsp> responseObserver){
        log.info("Agent accepted HeartBeat : {} , {}", heartReq.getNeId(), heartReq.getNeType());
        LambdaQueryWrapper<OamVnf> lambdaQueryWrapper = new LambdaQueryWrapper<OamVnf>().eq(OamVnf::getNeType, heartReq.getNeType())
                .eq(OamVnf::getNeId, heartReq.getNeId());
        OamVnf oamVnf = iOamVnfSelectService.getOne(lambdaQueryWrapper);
        HeartRsp heartRspToSimulator;
        if(VnfManagedStatus.equals(oamVnf.getVnfManageStatus())){
            heartBeatGrpcClient = heartBeatGrpcClient.getHeartBeatGrpcClient();
            HeartRsp heartRsp = heartBeatGrpcClient.heartBeatReport(heartReq.getNeType(), heartReq.getNeId());
            if(heartRsp != null && "0".equals(heartRsp.getResult())){
                heartRspToSimulator = HeartRsp.newBuilder().setResult("0").build();
            }else{
                heartRspToSimulator = HeartRsp.newBuilder().setResult("1").build();
            }
        }else{
            heartRspToSimulator = HeartRsp.newBuilder().setResult("0").build();
        }
        responseObserver.onNext(heartRspToSimulator);
        responseObserver.onCompleted();
    }
}
