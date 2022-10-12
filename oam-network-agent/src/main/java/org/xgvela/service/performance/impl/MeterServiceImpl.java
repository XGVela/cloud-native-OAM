package org.xgvela.service.performance.impl;

import org.xgvela.service.performance.IMeterService;
import io.micrometer.core.instrument.Clock;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Component
public class MeterServiceImpl implements IMeterService {
    private static double a = 1;

    private static final String suffix = "icore_upf_";

    private static Map<String, Gauge> gaugeMap = new HashMap<>();

    @Autowired
    private CollectorRegistry collectorRegistry;

    private Gauge gauge;
    private Gauge gg;

    //HA
    private Gauge icore_upf_UPF_PfcpSessionEstabReq;
    private Gauge icore_upf_UPF_PfcpSessionEstabReq_Ns;
    private Gauge icore_upf_UPF_PfcpSessionEstabReq_Dnn;
    private Gauge icore_upf_UPF_PfcpSessionEstabSucc;
    private Gauge icore_upf_UPF_PfcpSessionEstabSucc_Ns;
    private Gauge icore_upf_UPF_PfcpSessionEstabSucc_Dnn;
    private Gauge icore_upf_UPF_PfcpSessionEstabFail;
    private Gauge icore_upf_UPF_PfcpSessionEstabFail_Cause;
    private Gauge icore_upf_UPF_PfcpSessionEstabFail_Ns;
    private Gauge icore_upf_UPF_PfcpSessionEstabFail_Dnn;
    private Gauge icore_upf_UPF_PfcpSessionModifyReq;
    private Gauge icore_upf_UPF_PfcpSessionModifyReq_Ns;
    private Gauge icore_upf_UPF_PfcpSessionModifyReq_Dnn;
    private Gauge icore_upf_UPF_PfcpSessionModifySucc;
    private Gauge icore_upf_UPF_PfcpSessionModifySucc_Ns;
    private Gauge icore_upf_UPF_PfcpSessionModifySucc_Dnn;
    private Gauge icore_upf_UPF_PfcpSessionModifyFail;
    private Gauge icore_upf_UPF_PfcpSessionModifyFail_Cause;
    private Gauge icore_upf_UPF_PfcpSessionModifyFail_Ns;
    private Gauge icore_upf_UPF_PfcpSessionModifyFail_Dnn;

    //HB
    private Gauge icore_upf_UPF_MeanQosFlows;
    private Gauge icore_upf_UPF_MeanQosFlows_Ns;
    private Gauge icore_upf_UPF_MeanQosFlows_Dnn;
    private Gauge icore_upf_UPF_MaxQosFlows;
    private Gauge icore_upf_UPF_MaxQosFlows_Ns;
    private Gauge icore_upf_UPF_MaxQosFlows_Dnn;

    //HC
    private Gauge icore_upf_UPF_N3IncPkt;
    private Gauge icore_upf_UPF_N3IncPkt_Ns;
    private Gauge icore_upf_UPF_N3OgPkt;
    private Gauge icore_upf_UPF_N3OgPkt_Ns;
    private Gauge icore_upf_UPF_N3IncOct;
    private Gauge icore_upf_UPF_N3IncOct_Ns;
    private Gauge icore_upf_UPF_N3OgOct;
    private Gauge icore_upf_UPF_N3OgOct_Ns;
    private Gauge icore_upf_UPF_N3DiscPkt;
    private Gauge icore_upf_UPF_N3DiscPkt_Ns;

    //HD
    private Gauge icore_upf_UPF_N9aIncPkt;
    private Gauge icore_upf_UPF_N9aIncPkt_Ns;
    private Gauge icore_upf_UPF_N9aOgPkt;
    private Gauge icore_upf_UPF_N9aOgPkt_Ns;
    private Gauge icore_upf_UPF_N9aIncOct;
    private Gauge icore_upf_UPF_N9aIncOct_Ns;
    private Gauge icore_upf_UPF_N9aOgOct;
    private Gauge icore_upf_UPF_N9aOgOct_Ns;
    private Gauge icore_upf_UPF_N9aDiscPkt;
    private Gauge icore_upf_UPF_N9aDiscPkt_Ns;
    private Gauge icore_upf_UPF_N9cIncPkt;
    private Gauge icore_upf_UPF_N9cIncPkt_Ns;
    private Gauge icore_upf_UPF_N9cOgPkt;
    private Gauge icore_upf_UPF_N9cOgPkt_Ns;
    private Gauge icore_upf_UPF_N9cIncOct;
    private Gauge icore_upf_UPF_N9cIncOct_Ns;
    private Gauge icore_upf_UPF_N9cOgOct;
    private Gauge icore_upf_UPF_N9cOgOct_Ns;
    private Gauge icore_upf_UPF_N9cDiscPkt;
    private Gauge icore_upf_UPF_N9cDiscPkt_Ns;

    //HE
    private Gauge icore_upf_UPF_N6IncPkt;
    private Gauge icore_upf_UPF_N6IncPkt_Dnn;
    private Gauge icore_upf_UPF_N6OgPk;
    private Gauge icore_upf_UPF_N6OgPkt_Dnn;
    private Gauge icore_upf_UPF_N6IncOct;
    private Gauge icore_upf_UPF_N6IncOct_Dnn;
    private Gauge icore_upf_UPF_N6OgOct;
    private Gauge icore_upf_UPF_N6OgOct_Dnn;
    private Gauge icore_upf_UPF_N6DiscPkt;
    private Gauge icore_upf_UPF_N6DiscPkt_Dnn;

    //HF
    private Gauge icore_upf_ME_MeanMeLoad;

    @Bean
    @ConditionalOnMissingBean
    public PrometheusMeterRegistry prometheusMeterRegistry(PrometheusConfig prometheusConfig,
                                                           CollectorRegistry collectorRegistry, Clock clock) {
        return new PrometheusMeterRegistry(prometheusConfig, collectorRegistry, clock);
    }

    @Bean
    @ConditionalOnMissingBean
    public CollectorRegistry collectorRegistry() {
        return new CollectorRegistry(true);
    }

    @PostConstruct
    private void init(){

        gauge = Gauge.build()
                .name("Master_grpc_abc_fen")
                .help("metric data")
                .labelNames("neId", "neType", "job")
                .register(collectorRegistry);
        gg = Gauge.build()
                .name("slave_grpc_describe")
                .help("slave data")
                .labelNames("neId", "neType", "job")
                .register(collectorRegistry);

        //HA
        icore_upf_UPF_PfcpSessionEstabReq = Gauge.build().name(suffix + "UPF_PfcpSessionEstabReq").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabReq_Ns = Gauge.build().name(suffix + "UPF_PfcpSessionEstabReq_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabReq_Dnn = Gauge.build().name(suffix + "UPF_PfcpSessionEstabReq_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabSucc = Gauge.build().name(suffix + "UPF_PfcpSessionEstabSucc").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabSucc_Ns = Gauge.build().name(suffix + "UPF_PfcpSessionEstabSucc_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabSucc_Dnn = Gauge.build().name(suffix + "UPF_PfcpSessionEstabSucc_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabFail = Gauge.build().name(suffix + "UPF_PfcpSessionEstabFail").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabFail_Cause = Gauge.build().name(suffix + "UPF_PfcpSessionEstabFail_Cause").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabFail_Ns = Gauge.build().name(suffix + "UPF_PfcpSessionEstabFail_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionEstabFail_Dnn = Gauge.build().name(suffix + "UPF_PfcpSessionEstabFail_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyReq = Gauge.build().name(suffix + "UPF_PfcpSessionModifyReq").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyReq_Ns = Gauge.build().name(suffix + "UPF_PfcpSessionModifyReq_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyReq_Dnn = Gauge.build().name(suffix + "UPF_PfcpSessionModifyReq_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifySucc = Gauge.build().name(suffix + "UPF_PfcpSessionModifySucc").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifySucc_Ns = Gauge.build().name(suffix + "UPF_PfcpSessionModifySucc_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifySucc_Dnn = Gauge.build().name(suffix + "UPF_PfcpSessionModifySucc_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyFail = Gauge.build().name(suffix + "UPF_PfcpSessionModifyFail").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyFail_Cause = Gauge.build().name(suffix + "UPF_PfcpSessionModifyFail_Cause").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyFail_Ns = Gauge.build().name(suffix + "UPF_PfcpSessionModifyFail_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_PfcpSessionModifyFail_Dnn = Gauge.build().name(suffix + "UPF_PfcpSessionModifyFail_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);

        //HB
        icore_upf_UPF_MeanQosFlows = Gauge.build().name(suffix + "UPF_MeanQosFlows").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_MeanQosFlows_Ns = Gauge.build().name(suffix + "UPF_MeanQosFlows_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_MeanQosFlows_Dnn = Gauge.build().name(suffix + "UPF_MeanQosFlows_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_MaxQosFlows = Gauge.build().name(suffix + "UPF_MaxQosFlows").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_MaxQosFlows_Ns = Gauge.build().name(suffix + "UPF_MaxQosFlows_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_MaxQosFlows_Dnn = Gauge.build().name(suffix + "UPF_MaxQosFlows_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);

        //HC
        icore_upf_UPF_N3IncPkt = Gauge.build().name(suffix + "UPF_N3IncPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3IncPkt_Ns = Gauge.build().name(suffix + "UPF_N3IncPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3OgPkt = Gauge.build().name(suffix + "UPF_N3OgPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3OgPkt_Ns = Gauge.build().name(suffix + "UPF_N3OgPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3IncOct = Gauge.build().name(suffix + "UPF_N3IncOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3IncOct_Ns = Gauge.build().name(suffix + "UPF_N3IncOct_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3OgOct = Gauge.build().name(suffix + "UPF_N3OgOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3OgOct_Ns = Gauge.build().name(suffix + "UPF_N3OgOct_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3DiscPkt = Gauge.build().name(suffix + "UPF_N3DiscPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N3DiscPkt_Ns = Gauge.build().name(suffix + "UPF_N3DiscPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);

        //HD
        icore_upf_UPF_N9aIncPkt= Gauge.build().name(suffix + "UPF_N9aIncPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aIncPkt_Ns = Gauge.build().name(suffix + "UPF_N9aIncPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aOgPkt = Gauge.build().name(suffix + "UPF_N9aOgPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aOgPkt_Ns = Gauge.build().name(suffix + "UPF_N9aOgPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aIncOct = Gauge.build().name(suffix + "UPF_N9aIncOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aIncOct_Ns = Gauge.build().name(suffix + "UPF_N9aIncOct_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aOgOct = Gauge.build().name(suffix + "UPF_N9aOgOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aOgOct_Ns = Gauge.build().name(suffix + "UPF_N9aOgOct_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aDiscPkt = Gauge.build().name(suffix + "UPF_N9aDiscPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9aDiscPkt_Ns = Gauge.build().name(suffix + "UPF_N9aDiscPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cIncPkt = Gauge.build().name(suffix + "UPF_N9cIncPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cIncPkt_Ns = Gauge.build().name(suffix + "UPF_N9cIncPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cOgPkt = Gauge.build().name(suffix + "UPF_N9cOgPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cOgPkt_Ns = Gauge.build().name(suffix + "UPF_N9cOgPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cIncOct = Gauge.build().name(suffix + "UPF_N9cIncOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cIncOct_Ns = Gauge.build().name(suffix + "UPF_N9cIncOct_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cOgOct = Gauge.build().name(suffix + "UPF_N9cOgOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cOgOct_Ns = Gauge.build().name(suffix + "UPF_N9cOgOct_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cDiscPkt = Gauge.build().name(suffix + "UPF_N9cDiscPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N9cDiscPkt_Ns = Gauge.build().name(suffix + "UPF_N9cDiscPkt_Ns").help("metric data").labelNames("neId", "neType").register(collectorRegistry);

        //HE
        icore_upf_UPF_N6IncPkt = Gauge.build().name(suffix + "UPF_N6IncPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6IncPkt_Dnn = Gauge.build().name(suffix + "UPF_N6IncPkt_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6OgPk = Gauge.build().name(suffix + "UPF_N6OgPk").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6OgPkt_Dnn = Gauge.build().name(suffix + "UPF_N6OgPkt_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6IncOct = Gauge.build().name(suffix + "UPF_N6IncOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6IncOct_Dnn = Gauge.build().name(suffix + "UPF_N6IncOct_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6OgOct = Gauge.build().name(suffix + "UPF_N6OgOct").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6OgOct_Dnn = Gauge.build().name(suffix + "UPF_N6OgOct_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6DiscPkt = Gauge.build().name(suffix + "UPF_N6DiscPkt").help("metric data").labelNames("neId", "neType").register(collectorRegistry);
        icore_upf_UPF_N6DiscPkt_Dnn = Gauge.build().name(suffix + "UPF_N6DiscPkt_Dnn").help("metric data").labelNames("neId", "neType").register(collectorRegistry);

        //HF
        icore_upf_ME_MeanMeLoad = Gauge.build().name(suffix + "ME_MeanMeLoad").help("metric data").labelNames("neId", "neType").register(collectorRegistry);

        //HA
        gaugeMap.put("UPF_PfcpSessionEstabReq", icore_upf_UPF_PfcpSessionEstabReq);
        gaugeMap.put("UPF_PfcpSessionEstabReq_Ns", icore_upf_UPF_PfcpSessionEstabReq_Ns);
        gaugeMap.put("UPF_PfcpSessionEstabReq_Dnn", icore_upf_UPF_PfcpSessionEstabReq_Dnn);
        gaugeMap.put("UPF_PfcpSessionEstabSucc", icore_upf_UPF_PfcpSessionEstabSucc);
        gaugeMap.put("UPF_PfcpSessionEstabSucc_Ns", icore_upf_UPF_PfcpSessionEstabSucc_Ns);
        gaugeMap.put("UPF_PfcpSessionEstabSucc_Dnn", icore_upf_UPF_PfcpSessionEstabSucc_Dnn);
        gaugeMap.put("UPF_PfcpSessionEstabFail", icore_upf_UPF_PfcpSessionEstabFail);
        gaugeMap.put("UPF_PfcpSessionEstabFail_Cause", icore_upf_UPF_PfcpSessionEstabFail_Cause);
        gaugeMap.put("UPF_PfcpSessionEstabFail_Ns", icore_upf_UPF_PfcpSessionEstabFail_Ns);
        gaugeMap.put("UPF_PfcpSessionEstabFail_Dnn", icore_upf_UPF_PfcpSessionEstabFail_Dnn);
        gaugeMap.put("UPF_PfcpSessionModifyReq", icore_upf_UPF_PfcpSessionModifyReq);
        gaugeMap.put("UPF_PfcpSessionModifyReq_Ns", icore_upf_UPF_PfcpSessionModifyReq_Ns);
        gaugeMap.put("UPF_PfcpSessionModifyReq_Dnn", icore_upf_UPF_PfcpSessionModifyReq_Dnn);
        gaugeMap.put("UPF_PfcpSessionModifySucc", icore_upf_UPF_PfcpSessionModifySucc);
        gaugeMap.put("UPF_PfcpSessionModifySucc_Ns", icore_upf_UPF_PfcpSessionModifySucc_Ns);
        gaugeMap.put("UPF_PfcpSessionModifySucc_Dnn", icore_upf_UPF_PfcpSessionModifySucc_Dnn);
        gaugeMap.put("UPF_PfcpSessionModifyFail", icore_upf_UPF_PfcpSessionModifyFail);
        gaugeMap.put("UPF_PfcpSessionModifyFail_Cause", icore_upf_UPF_PfcpSessionModifyFail_Cause);
        gaugeMap.put("UPF_PfcpSessionModifyFail_Ns", icore_upf_UPF_PfcpSessionModifyFail_Ns);
        gaugeMap.put("UPF_PfcpSessionModifyFail_Dnn", icore_upf_UPF_PfcpSessionModifyFail_Dnn);

        //HB
        gaugeMap.put("UPF_MeanQosFlows", icore_upf_UPF_MeanQosFlows);
        gaugeMap.put("UPF_MeanQosFlows_Ns", icore_upf_UPF_MeanQosFlows_Ns);
        gaugeMap.put("UPF_MeanQosFlows_Dnn", icore_upf_UPF_MeanQosFlows_Dnn);
        gaugeMap.put("UPF_MaxQosFlows", icore_upf_UPF_MaxQosFlows);
        gaugeMap.put("UPF_MaxQosFlows_Ns", icore_upf_UPF_MaxQosFlows_Ns);
        gaugeMap.put("UPF_MaxQosFlows_Dnn", icore_upf_UPF_MaxQosFlows_Dnn);

        //HC
        gaugeMap.put("UPF_N3IncPkt", icore_upf_UPF_N3IncPkt);
        gaugeMap.put("UPF_N3IncPkt_Ns", icore_upf_UPF_N3IncPkt_Ns);
        gaugeMap.put("UPF_N3OgPkt", icore_upf_UPF_N3OgPkt);
        gaugeMap.put("UPF_N3OgPkt_Ns", icore_upf_UPF_N3OgPkt_Ns);
        gaugeMap.put("UPF_N3IncOct", icore_upf_UPF_N3IncOct);
        gaugeMap.put("UPF_N3IncOct_Ns", icore_upf_UPF_N3IncOct_Ns);
        gaugeMap.put("UPF_N3OgOct", icore_upf_UPF_N3OgOct);
        gaugeMap.put("UPF_N3OgOct_Ns", icore_upf_UPF_N3OgOct_Ns);
        gaugeMap.put("UPF_N3DiscPkt", icore_upf_UPF_N3DiscPkt);
        gaugeMap.put("UPF_N3DiscPkt_Ns", icore_upf_UPF_N3DiscPkt_Ns);

        //HD
        gaugeMap.put("UPF_N9aIncPkt", icore_upf_UPF_N9aIncPkt);
        gaugeMap.put("UPF_N9aIncPkt_Ns", icore_upf_UPF_N9aIncPkt_Ns);
        gaugeMap.put("UPF_N9aOgPkt", icore_upf_UPF_N9aOgPkt);
        gaugeMap.put("UPF_N9aOgPkt_Ns", icore_upf_UPF_N9aOgPkt_Ns);
        gaugeMap.put("UPF_N9aIncOct", icore_upf_UPF_N9aIncOct);
        gaugeMap.put("UPF_N9aIncOct_Ns", icore_upf_UPF_N9aIncOct_Ns);
        gaugeMap.put("UPF_N9aOgOct", icore_upf_UPF_N9aOgOct);
        gaugeMap.put("UPF_N9aOgOct_Ns", icore_upf_UPF_N9aOgOct_Ns);
        gaugeMap.put("UPF_N9aDiscPkt", icore_upf_UPF_N9aDiscPkt);
        gaugeMap.put("UPF_N9aDiscPkt_Ns", icore_upf_UPF_N9aDiscPkt_Ns);
        gaugeMap.put("UPF_N9cIncPkt", icore_upf_UPF_N9cIncPkt);
        gaugeMap.put("UPF_N9cIncPkt_Ns", icore_upf_UPF_N9cIncPkt_Ns);
        gaugeMap.put("UPF_N9cOgPkt", icore_upf_UPF_N9cOgPkt);
        gaugeMap.put("UPF_N9cOgPkt_Ns", icore_upf_UPF_N9cOgPkt_Ns);
        gaugeMap.put("UPF_N9cIncOct", icore_upf_UPF_N9cIncOct);
        gaugeMap.put("UPF_N9cIncOct_Ns", icore_upf_UPF_N9cIncOct_Ns);
        gaugeMap.put("UPF_N9cOgOct", icore_upf_UPF_N9cOgOct);
        gaugeMap.put("UPF_N9cOgOct_Ns", icore_upf_UPF_N9cOgOct_Ns);
        gaugeMap.put("UPF_N9cDiscPkt", icore_upf_UPF_N9cDiscPkt);
        gaugeMap.put("UPF_N9cDiscPkt_Ns", icore_upf_UPF_N9cDiscPkt_Ns);

        //HE
        gaugeMap.put("UPF_N6IncPkt", icore_upf_UPF_N6IncPkt);
        gaugeMap.put("UPF_N6IncPkt_Dnn", icore_upf_UPF_N6IncPkt_Dnn);
        gaugeMap.put("UPF_N6OgPk", icore_upf_UPF_N6OgPk);
        gaugeMap.put("UPF_N6OgPkt_Dnn", icore_upf_UPF_N6OgPkt_Dnn);
        gaugeMap.put("UPF_N6IncOct", icore_upf_UPF_N6IncOct);
        gaugeMap.put("UPF_N6IncOct_Dnn", icore_upf_UPF_N6IncOct_Dnn);
        gaugeMap.put("UPF_N6OgOct", icore_upf_UPF_N6OgOct);
        gaugeMap.put("UPF_N6OgOct_Dnn", icore_upf_UPF_N6OgOct_Dnn);
        gaugeMap.put("UPF_N6DiscPkt", icore_upf_UPF_N6DiscPkt);
        gaugeMap.put("UPF_N6DiscPkt_Dnn", icore_upf_UPF_N6DiscPkt_Dnn);

        //HF
        gaugeMap.put("ME_MeanMeLoad", icore_upf_ME_MeanMeLoad);
    }

    @Override
    public void MetricData() {
        a ++;
        Double amfValue = new Random(10).nextDouble() + a;
        Double ausfValue = new Random(100).nextDouble() + a;
        System.out.println(amfValue);
        System.out.println(ausfValue);
        gauge.labels("amfinstanceid230", "AMF", "exporter").set(amfValue);
        gauge.labels("ausfinstanceid230", "AUSF", "exporter").set(ausfValue);

        gg.labels("slave1", "AUSF", "exporter").set(ausfValue);

        System.out.println("111");
    }

    @Override
    public void MetricGrpcValue(String neId, String neType, String metric, Double value) {
        Gauge gg = gaugeMap.get(metric);
        if (gg != null) {
            gg.labels(neId, neType).set(value);
        }
    }

    @Override
    public void MetricGrpcDefaultValue(String neId, String neType) {
        for (Gauge gauge : gaugeMap.values()) {
            gauge.labels(neId, neType).set(0D);
        }
    }

    private Gauge getGaugeClient(String name, String help) {
        return Gauge.build()
                .name(name)
                .help(help)
                .labelNames("neId", "neType")
                .register(collectorRegistry);
    }
}
