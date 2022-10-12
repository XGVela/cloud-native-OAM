package org.xgvela.controller.impl;

import org.xgvela.controller.IMetricController;
import org.xgvela.service.performance.IMeterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class MetricControllerImpl implements IMetricController {

    @Autowired
    private IMeterService meterService;

    @Override
    public String MetricData() {
        meterService.MetricData();
        return "metric data";
    }
}
