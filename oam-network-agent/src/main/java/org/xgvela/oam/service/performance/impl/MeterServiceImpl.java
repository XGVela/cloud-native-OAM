package org.xgvela.oam.service.performance.impl;

import org.xgvela.oam.service.performance.IMeterService;
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
import java.util.Random;

@Service
@Component
public class MeterServiceImpl implements IMeterService {
    private static double a = 1;

    @Autowired
    private CollectorRegistry collectorRegistry;

    private Gauge gauge;
    private Gauge gg;

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
        getGaugeClient(getReplaceAllString(metric), metric).labels(neId, neType).set(value);
    }

    private Gauge getGaugeClient(String name, String help) {
        return Gauge.build()
                .name(name)
                .help(help)
                .labelNames("neId", "neType")
                .register(collectorRegistry);
    }
    private String getReplaceAllString(String name) {
        return name.replaceAll("._", "_").replaceAll("\\.", "_");
    }
}
