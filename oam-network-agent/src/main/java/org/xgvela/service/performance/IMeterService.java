package org.xgvela.service.performance;

public interface IMeterService {
    void MetricData();

    void MetricGrpcValue(String neId, String neType, String metric, Double value);

    void MetricGrpcDefaultValue(String neId, String neType);
}
