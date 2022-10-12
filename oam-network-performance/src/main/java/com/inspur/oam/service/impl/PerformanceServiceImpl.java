package org.xgvela.oam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.xgvela.oam.entity.PerfData;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.service.IPerformanceService;
import org.xgvela.oam.utils.PrometheusUtils;
import org.xgvela.oam.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PerformanceServiceImpl implements IPerformanceService {

    private final static String COMMA = ",";

    @Value("${prometheus.host}")
    private String prometheusHost;

    @Autowired
    private OamVnfMapper oamVnfMapper;

    @Override
    public List<PerfData> listQuery(String apiVersion, String elementTypeValue, String objectTypeValue, String rmUIDs, String measurementCategorys) {
        List<PerfData> perfDataList = new ArrayList<>();
        List<String> neIds = Arrays.asList(rmUIDs.split(COMMA));
        Set<String> neIdSets = oamVnfMapper.selectList(new LambdaQueryWrapper<OamVnf>().in(OamVnf::getNeId, neIds).eq(OamVnf::getVnfManageStatus, "1")).stream().map(OamVnf::getNeId).collect(Collectors.toSet());

        Set<String> measurementSet = new HashSet<>();
        if (Objects.nonNull(measurementCategorys)) {
            Arrays.asList(measurementCategorys.split(COMMA)).forEach(measurement -> {
                measurementSet.add(measurement);
            });
        }

        //查询prometheus数据
        String querySql = "{__name__=~\"icore_.*\"}";
        Long time = System.currentTimeMillis();
        JsonNode jsonNode = PrometheusUtils.query(prometheusHost, querySql, time);
        JsonNode result = jsonNode.get("data").get("result");
        if (result.size() > 0 && neIdSets.size() > 0) {
            Map<String, Map<String, Object>> perf = new HashMap<>(); // {neId : {perf : val}}
            for (int i = 0; i < result.size(); i++) {
                JsonNode row = result.get(i);
                Object metricId = row.get("metric").get("neId");
                if (Objects.isNull(metricId)) {
                    continue;
                }
                String neId = metricId.toString().replaceAll("\"", "");
                if (neIdSets.contains(neId)) {
                    String enName = row.get("metric").get("__name__").toString().replaceAll("\"", "").replace("icore_upf", "");
                    String value = row.get("value").get(1).toString().replaceAll("\"", "");

                    Map<String, Object> perfMap = perf.get(neId);
                    if (Objects.nonNull(perfMap)) {
                        perfMap.put(enName, value);
                    } else {
                        Map<String, Object> newPerfMap = new HashMap<>();
                        newPerfMap.put(enName, value);
                        perf.put(neId, newPerfMap);
                    }
                }
            }
            //根据参数赋值数据
            for (String neId : perf.keySet()) {
                List<String> measurementCategorysList = new ArrayList<>();
                List<Object> measurementResultsList = new ArrayList<>();
                for (Map.Entry<String, Object> entry : perf.get(neId).entrySet()) {
                    if (measurementSet.size() > 0 && !measurementSet.contains(entry.getKey()) ) {
                        continue;
                    }
                    measurementCategorysList.add(entry.getKey());
                    measurementResultsList.add(entry.getValue());
                }
                perfDataList.add(PerfData.builder()
                        .rmUID(neId)
                        .resultTime(TimeUtil.dateTimeToStr(time))
                        .measurementCategorys(measurementCategorysList)
                        .measurementResults(measurementResultsList).build());
            }
        }

        return perfDataList;
    }
}
