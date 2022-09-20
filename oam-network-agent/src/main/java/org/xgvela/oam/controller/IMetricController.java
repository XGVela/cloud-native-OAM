package org.xgvela.oam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "agent-pm")
@RestController
@RequestMapping(value = "/metricTest")
public interface IMetricController {

    @ApiOperation(value = "metric pm data", notes = "metric pm data")
    @GetMapping
    String MetricData();
}
