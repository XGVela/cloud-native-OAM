package org.xgvela.oam.service.config;

import org.xgvela.oam.configuremanager.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
@Slf4j
public class ConfigureManagerService extends ConfigureManagerServiceGrpc.ConfigureManagerServiceImplBase {
    @Override
    public StreamObserver<UpdateCfgFileReq> updateConfigFile(StreamObserver<UpdateCfgFileRsps> updateRspsStreamObserver) {
        MyStreamObserver observer = new MyStreamObserver();
        observer.setResponseObserver(updateRspsStreamObserver);
        return observer;
    }

    static class MyStreamObserver implements StreamObserver<UpdateCfgFileReq> {
        private StreamObserver<UpdateCfgFileRsps> responseObserver;
        private UpdateCfgFileRsps updateCfgFileRsps;

        public void setResponseObserver(StreamObserver<UpdateCfgFileRsps> responseObserver) {
            this.responseObserver = responseObserver;
        }

        @Override
        public void onNext(UpdateCfgFileReq value) {
            log.info("Agent ConfigureManagerService updateConfigFile accepted fileName：{}", value.getFileName());
            UpdateCfgFileRsp updateCfgFileRsp1 = UpdateCfgFileRsp.newBuilder().setUpdateRsp("0").build();
            UpdateCfgFileRsp updateCfgFileRsp2 = UpdateCfgFileRsp.newBuilder().setUpdateRsp("0").build();
            this.updateCfgFileRsps = UpdateCfgFileRsps.newBuilder().addUpdateRsp(updateCfgFileRsp1).addUpdateRsp(updateCfgFileRsp2).build();
        }

        @Override
        public void onError(Throwable t) {
            log.info("Exception::", t);
        }

        @Override
        public void onCompleted() {
            responseObserver.onNext(updateCfgFileRsps);
            responseObserver.onCompleted();
        }
    }
}
