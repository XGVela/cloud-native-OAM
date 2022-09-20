package org.xgvela.oam.service.config;

import com.google.protobuf.ByteString;
import org.xgvela.oam.syncconfig.GetFileResp;
import org.xgvela.oam.syncconfig.SyncConfigServiceGrpc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@GrpcService
@AllArgsConstructor
@Slf4j
public class SyncConfigService extends SyncConfigServiceGrpc.SyncConfigServiceImplBase {
    private static List<GetFileResp> mockOrders() {
        GetFileResp.Builder builder = GetFileResp.newBuilder();
        return IntStream.range(0, 10).mapToObj(i -> builder.setFileData(ByteString.copyFromUtf8(String.valueOf(i))).setResult("0").build()).collect(Collectors.toList());
    }

    @Override
    public void getConfigFile(org.xgvela.oam.syncconfig.GetFileReq request, io.grpc.stub.StreamObserver<org.xgvela.oam.syncconfig.GetFileResp> responseObserver) {
        mockOrders().forEach(getFileResp -> {
            responseObserver.onNext(getFileResp);
            log.info("Agent SyncConfigService getConfigFile {}, {}", getFileResp.getResult(), getFileResp.getFileData());
        });
        responseObserver.onCompleted();
    }
}
