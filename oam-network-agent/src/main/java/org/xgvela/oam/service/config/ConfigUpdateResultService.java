package org.xgvela.oam.service.config;

import org.xgvela.oam.configupdateresult.CfgResultNotifyResp;
import org.xgvela.oam.configupdateresult.ConfigUpdateResultServiceGrpc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
@Slf4j
public class ConfigUpdateResultService extends ConfigUpdateResultServiceGrpc.ConfigUpdateResultServiceImplBase {

    @Override
    public void cfgUpdateResult(org.xgvela.oam.configupdateresult.CfgResultNotifyReq request, io.grpc.stub.StreamObserver<org.xgvela.oam.configupdateresult.CfgResultNotifyResp> responseObserver) {
        log.info("Agent ConfigUpdateResultService accepted cfgUpdateResult : {}", request);
        CfgResultNotifyResp cfgResultNotifyResp = CfgResultNotifyResp.newBuilder().setResult("0").build();
        responseObserver.onNext(cfgResultNotifyResp);
        responseObserver.onCompleted();
    }
}
