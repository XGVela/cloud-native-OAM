package org.xgvela.oam.service.performance;

import org.xgvela.oam.performance.PerfStatisticsServiceGrpc;
import org.xgvela.oam.performance.StatsInfoReq;
import org.xgvela.oam.performance.StatsInfoRsp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

@GrpcService
@AllArgsConstructor
@Slf4j
public class PerformanceService extends PerfStatisticsServiceGrpc.PerfStatisticsServiceImplBase {

    private final static String neId = "upfinstanceid002";
    private final static String neType = "UPF";

    @Autowired
    private IMeterService meterService;

    @Override
    public void statisticsReq(StatsInfoReq request,
                              io.grpc.stub.StreamObserver<StatsInfoRsp> responseObserver) {
        log.info("Agent PerformanceService : {}", request);
        StatsInfoRsp statsInfoRsp = StatsInfoRsp.newBuilder().setResult("0").build();
        Map<String, Double> statMap = request.getStatsResultInfoMap();
        if (Objects.nonNull(statMap)) {
            for (String metric : statMap.keySet()) {
                meterService.MetricGrpcValue(neId, neType, metric, statMap.get(metric));
            }
        }
        responseObserver.onNext(statsInfoRsp);
        responseObserver.onCompleted();
    }
}
