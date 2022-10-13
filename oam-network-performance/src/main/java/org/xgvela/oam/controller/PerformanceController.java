package org.xgvela.oam.controller;

import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.IPerformanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Performance")
@RestController
@RequestMapping("/api/rest/performanceManagement")
public class PerformanceController {

    @Autowired
    private IPerformanceService performanceService;

    @ApiOperation(value = "Query performance data ", notes =" Query performance data ")
    @GetMapping("/{apiVersion}/elementType/{elementTypeValue}/objectType/{objectTypeValue}")
    public Response listQuery(@PathVariable(value = "apiVersion") String apiVersion,
                              @PathVariable(value = "elementTypeValue") String elementTypeValue,
                              @PathVariable(value = "objectTypeValue") String objectTypeValue,
                              @RequestParam(value = "rmUIDs") String rmUIDs,
                              @RequestParam(value = "measurementCategorys", required = false) String measurementCategorys) {
        return ResponseFactory.getSuccessData(performanceService.listQuery(apiVersion, elementTypeValue, objectTypeValue, rmUIDs, measurementCategorys));
    }
}
