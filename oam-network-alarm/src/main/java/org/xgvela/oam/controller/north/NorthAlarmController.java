package org.xgvela.oam.controller.north;


import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import org.xgvela.oam.entity.AlarmVo;
import org.xgvela.oam.service.north.INorthAlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@Api(tags = "north-alarm manage")
@RestController
@RequestMapping("/api/rest")
public class NorthAlarmController {

    @Autowired
    private INorthAlarmService northAlarmService;


    @ApiOperation(value = "search active alarm", notes = "search active alarm")
    @GetMapping("/{apiVersion}/alarms")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apiVersion", value = "apiVersion", required = true),
            @ApiImplicitParam(name = "alarmIds", value = "alarmIds", required = true)
    })
    public Response<List<AlarmVo>> alarms(@PathVariable String apiVersion, String alarmIds) {
        return ResponseFactory.getSuccessData(northAlarmService.alarms(apiVersion, alarmIds));
    }

}
