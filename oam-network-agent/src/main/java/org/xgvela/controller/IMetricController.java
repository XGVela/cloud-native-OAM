package org.xgvela.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Proxy layer - performance ")
@RestController
@RequestMapping(value = "/metricTest")
public interface IMetricController {

    @ApiOperation(value = "metric performance data ", notes = "metric performance data ")
    @GetMapping
    String MetricData();
}