package org.xgvela.oam.controller.north;

import org.xgvela.oam.entity.AlarmVo;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.north.INorthAlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api(tags = "Northbound shared layer - Alarm management")
@RestController
@RequestMapping("/api/rest")
public class NorthAlarmController {

    @Autowired
    private INorthAlarmService northAlarmService;


    @ApiOperation(value = "Querying Real-Time Alarms", notes = "Querying Real-Time Alarms")
    @GetMapping("/faultManagement/{apiVersion}/alarms")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiVersion", value = "apiVersion", required = true),
            @ApiImplicitParam(name = "alarmIds", value = "alarmIds", required = false),
            @ApiImplicitParam(name = "startTime", value = "startTime", required = false),
            @ApiImplicitParam(name = "endTime", value = "endTime", required = false),
            @ApiImplicitParam(name = "neId", value = "neId", required = false),
            @ApiImplicitParam(name = "neType", value = "neType",  required = false),
            @ApiImplicitParam(name = "alarmLevel", value = "alarmLevel",  required = false),
            @ApiImplicitParam(name = "alarmTitle", value = "alarmTitle",  required = false)
    })
    public Response<List<AlarmVo>> alarms(@PathVariable String apiVersion, String alarmIds,
                                          String startTime, String endTime, String neId, String neType,
                                          String alarmLevel, String alarmTitle) {
        return ResponseFactory.getSuccessData(northAlarmService.alarms(apiVersion, alarmIds, startTime, endTime, neId, neType, alarmLevel, alarmTitle));
    }

}
