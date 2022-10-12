package org.xgvela.service.performance;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.mapper.subscribe.OamSubscribeMapper;
import org.xgvela.oam.performance.PerfStatisticsServiceGrpc;
import org.xgvela.oam.performance.StatsInfoReq;
import org.xgvela.oam.performance.StatsInfoRsp;
import org.xgvela.service.kafkaclient.KafkaProducerService;
import org.xgvela.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
@Slf4j
public class PerformanceService extends PerfStatisticsServiceGrpc.PerfStatisticsServiceImplBase {


    private static final String PerfSubscribeTopic = "perf_sub_pub";

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private IMeterService meterService;

    @Autowired
    private OamVnfMapper oamVnfMapper;

    @Autowired
    private OamSubscribeMapper oamSubscribeMapper;

    @Override
    public void statisticsReq(StatsInfoReq request,
                              io.grpc.stub.StreamObserver<StatsInfoRsp> responseObserver) {
        log.info("Agent PerformanceService : {}", request);
        StatsInfoRsp statsInfoRsp = StatsInfoRsp.newBuilder().setResult("0").build();
        Map<String, Double> statMap = request.getStatsResultInfoMap();
        String neId = request.getNeId();
        //List<OamVnf> oamVnfList = oamVnfMapper.selectList(new LambdaQueryWrapper<OamVnf>().eq(OamVnf::getNeId, neId));
        Map<String, OamVnf> oamVnfMap = oamVnfMapper.selectList(new LambdaQueryWrapper<OamVnf>()).stream().collect(Collectors.toMap(k1 -> k1.getNeId(), k2 -> k2));
        List<OamSubscribe> oamSubscribeList  = oamSubscribeMapper.selectList(new LambdaQueryWrapper<OamSubscribe>()).stream().collect(Collectors.toList());
        if (Objects.nonNull(oamVnfMap) && oamVnfMap.size() > 0) {
            OamVnf oamVnf = oamVnfMap.get(neId);
            if (Objects.nonNull(oamVnf)) {
                if ("1".equals(oamVnf.getVnfManageStatus())) {
                    Map<String, Object> perfSubMap = new HashMap<>();
                    perfSubMap.put("neId", neId);
                    perfSubMap.put("neType", oamVnf.getNeType());
                    for (String metric : statMap.keySet()) {
                        String name = getReplaceAllString(metric);
                        meterService.MetricGrpcValue(neId, oamVnf.getNeType(), name, statMap.get(metric));

                        perfSubMap.put(name, statMap.get(metric));
                    }
                    log.info("prometheus push online data: " +neId);
                    for (int i = 0; i < oamSubscribeList.size(); i++) {
                        OamSubscribe oamSubscribe = oamSubscribeList.get(i);
                        if (neId.equals(oamSubscribe.getNeId())) {
                            if (oamSubscribe.getDataType() != null) {
                                String[] dataTypes = oamSubscribe.getDataType().split(",");
                                if (Arrays.asList(dataTypes).contains("perf")) {
                                    Boolean sendResult = kafkaProducerService.send(PerfSubscribeTopic,  JSON.toJSONStringWithDateFormat(perfSubMap, "yyyy-MM-dd HH:mm:ss"));
                                    log.info("agent push perf subscribe message: " + JsonUtils.o2js(perfSubMap));
                                }
                            }
                        }
                    }

                } else {
                    meterService.MetricGrpcDefaultValue(neId, oamVnf.getNeType());
                    log.info("prometheus push offline data: " + neId);
                }

            }

        }

        responseObserver.onNext(statsInfoRsp);
        responseObserver.onCompleted();
    }

    private String getReplaceAllString(String name) {
        return name.replaceAll("._", "_").replaceAll("\\.", "_");
    }
}
