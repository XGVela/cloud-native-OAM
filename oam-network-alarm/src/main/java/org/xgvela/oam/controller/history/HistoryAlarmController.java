package org.xgvela.oam.controller.history;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.security.entity.Developer;
import com.inspur.cnet.security.service.DeveloperServiceImpl;
import com.inspur.cnet.security.service.utils.SecurityUtils;
import org.xgvela.oam.controller.BaseResourceController;
import org.xgvela.oam.entity.alarm.history.ClearedAlarmSeverity;
import org.xgvela.oam.entity.alarm.history.HistoryAlarm;
import org.xgvela.oam.service.history.ClearedAlarmSeverityServiceImpl;
import org.xgvela.oam.service.history.HistoryAlarmServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * HistoryAlarmController
 * </p>
 */
@Api(tags = "alarm manage-history alarm")
@RestController
@RequestMapping("api/history-alarms")
public class HistoryAlarmController extends BaseResourceController {
    
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;

    @Autowired
    private DeveloperServiceImpl developerService;
    @Autowired
    private ClearedAlarmSeverityServiceImpl clearedAlarmSeverityService;

    @ApiOperation(value = "create", notes = "create")
    @PostMapping
    public Response<HistoryAlarm> create(@RequestBody HistoryAlarm historyAlarm) {
        return resourceResponse(developerId ->historyAlarmService.create(historyAlarm,developerId));
    }

    @ApiOperation(value = "update", notes = "update")
    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id,
                                    @RequestBody HistoryAlarm historyAlarm) {
        historyAlarm.setId(id);
        return resourceResponse(developerId ->historyAlarmService.update(id, developerId, historyAlarm));
    }

    @ApiOperation(value = "delete", notes = "delete")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        return resourceResponse(developerId ->historyAlarmService.delete(id, developerId));
    }

    @ApiOperation(value = "getHistoryAlarm", notes = "getHistoryAlarm")
    @GetMapping("/{id}")
    public Response<HistoryAlarm> getHistoryAlarm(@PathVariable Long id) {
        return resourceResponse(developerId ->historyAlarmService.getById(id));
    }

    @ApiOperation(value = "list", notes = "list")
    @GetMapping("/list")
    public Response<List<HistoryAlarm>> list(@RequestParam(value = "alarmId", required = false) String alarmId,
                                  @RequestParam(value = "alarmNO", required = false) String alarmNO,
                                  @RequestParam(value = "alarmObjectUid", required = false) String alarmObjectUid,
                                  @RequestParam(value = "alarmObjectName", required = false) String alarmObjectName,
                                  @RequestParam(value = "alarmName", required = false) String alarmName,
                                  @RequestParam(value = "alarmPvFlag", required = false) Integer alarmPvFlag,
                                  @RequestParam(value = "alarmType", required = false) Integer alarmType,
                                  @RequestParam(value = "alarmLevel", required = false) Integer alarmLevel,
                                  @RequestParam(value = "startTime", required = false) String startTime,
                                  @RequestParam(value = "endTime", required = false) String endTime,
                                  @RequestParam(value = "columnName", required = false) String columnName,
                                  @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return resourceResponse(developerId ->historyAlarmService.listHistoryAlarm(alarmId, alarmNO, alarmObjectUid, alarmObjectName, alarmName, alarmPvFlag, alarmType, alarmLevel, startTime, endTime, developerId, columnName, sortOrder));
    }

    @ApiOperation(value = "page", notes = "page")
    @GetMapping("/page")
    public Response<IPage<HistoryAlarm>> page(@RequestParam(value = "alarmId", required = false) String alarmId,
                                              @RequestParam(value = "alarmNO", required = false) String alarmNO,
                                              @RequestParam(value = "alarmObjectUid", required = false) String alarmObjectUid,
                                              @RequestParam(value = "alarmObjectName", required = false) String alarmObjectName,
                                              @RequestParam(value = "alarmName", required = false) String alarmName,
                                              @RequestParam(value = "alarmPvFlag", required = false) Integer alarmPvFlag,
                                              @RequestParam(value = "alarmType", required = false) Integer alarmType,
                                              @RequestParam(value = "alarmLevel", required = false) Integer alarmLevel,
                                              @RequestParam(value = "startTime", required = false) String startTime,
                                              @RequestParam(value = "endTime", required = false) String endTime,
                                              @RequestParam(value = "page", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "25") int pageSize,
                                              @RequestParam(value = "columnName", required = false) String columnName,
                                              @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return resourceResponse(developerId ->historyAlarmService.pageHistoryAlarm(alarmId, alarmNO, alarmObjectUid, alarmObjectName, alarmName, alarmPvFlag, alarmType,
                alarmLevel, developerId, startTime, endTime, pageNum, pageSize, columnName, sortOrder));
    }
    
    @ApiOperation(value = "deleteByDate", notes = "deleteByDate")
    @DeleteMapping("/date")
    public Response<Boolean> deleteByDate(@RequestParam(value = "date", required = true) long date){
        return resourceResponse(developerId ->historyAlarmService.deleteByDate(date));
    }


    @ApiOperation(value = "clearedSeverity")
    @GetMapping(value = "/cleared/severity")
    public Response<List<ClearedAlarmSeverity>> clearedSeverity(
            @RequestParam(value = "neName", required = false) String neName,
            @RequestParam(value = "alarmNO", required = false) String alarmNO,
            @RequestParam(value = "alarmObjectName", required = false) String alarmObjectName,
            @RequestParam(value = "alarmName", required = false) String alarmName,
            @RequestParam(value = "alarmPvFlag", required = false) Integer alarmPvFlag,
            @RequestParam(value = "alarmLevel", required = false) Integer alarmLevel,
            @RequestParam(value = "alarmType", required = false) Integer alarmType,
            @RequestParam(value = "source", required = false) String source,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime, HttpServletRequest request){
        return resourceResponse(developerId ->clearedAlarmSeverityService.countSeverity(neName, alarmNO, alarmObjectName,alarmName,alarmPvFlag, alarmLevel, alarmType, source, startTime,
             endTime,"cleared",developerId));
    }

    @ApiOperation(value = "alarmCount")
    @GetMapping(value = "/cleared/alarmCount")
    public Response<Long> alarmCount(
            @RequestParam(value = "alarmId", required = false) String alarmId){
        return resourceResponse(developerId ->
               clearedAlarmSeverityService.countAlarm(alarmId,developerId,"cleared"));
    }

    @ApiOperation(value = "configAlarmRetentionTime")
    @GetMapping(value = "/configAlarmTime")
    public Response<Integer> configAlarmRetentionTime(@RequestParam(value = "day" , required = true) Integer day){
        return null;
    }
}
