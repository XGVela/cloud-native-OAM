package org.xgvela.oam.controller.active;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.controller.BaseResourceController;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.active.ActiveAlarmClass;
import org.xgvela.oam.entity.alarm.active.ActiveAlarmSeverity;
import org.xgvela.oam.entity.alarm.statistics.ActiveAlarmStatisticsDTO;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.alarm.AcitveAlarmClassServiceImpl;
import org.xgvela.oam.service.alarm.AcitveAlarmSeverityServiceImpl;
import org.xgvela.oam.service.alarm.ActiveAlarmServiceImpl;
import org.xgvela.oam.service.alarm.AlarmService;
import org.xgvela.oam.service.history.HistoryAlarmServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * ActiveAlarmController
 * </p>
 */
@Api(tags = "Alarm Management - Real-time alarms")
@RestController
@RequestMapping("api/active-alarms")
public class ActiveAlarmController extends BaseResourceController {

    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;
    @Autowired
    private AlarmService alarmService;
    /*@Autowired
    private UserServiceImpl userService;
    @Autowired
    private DeveloperServiceImpl developerService;*/
    @Autowired
    private AcitveAlarmSeverityServiceImpl activeAlarmSeverityService;
    @Autowired
    private AcitveAlarmClassServiceImpl activeAlarmClassService;


    @ApiOperation(value = "Adding Real-time Alarms", notes = "Adding Real-time Alarms")
    @PostMapping
    public Response<ActiveAlarm> create(@RequestBody ActiveAlarm activeAlarm) {
        return resourceResponse(developerId ->activeAlarmService.create(activeAlarm,developerId));
    }

    @ApiOperation(value = "Editing Real-time Alarms", notes = "Editing Real-time Alarms")
    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id,
                          @RequestBody ActiveAlarm activeAlarm) {
        activeAlarm.setId(id);
        return resourceResponse(developerId ->activeAlarmService.update(id, developerId, activeAlarm));
    }

    @ApiOperation(value = "Real-time alarm Confirmation", notes = "Real-time alarm Confirmation")
    @PutMapping("/{id}/confirm")
    public Response<Boolean> confirm(@PathVariable Long id,
                           @RequestParam(value = "ackState") Integer ackState) {
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        String token = currentUser.getPrincipal().toString();
        Object userId = alarmService.decodeToken(token, "userId");
        Object account = alarmService.decodeToken(token, "account");
        return resourceResponse(developerId ->activeAlarmService.confirm(id, developerId, ackState, String.valueOf(userId), String.valueOf(account)));
    }

    @ApiOperation(value = "Deleting a Real-Time Alarm", notes = "Deleting a Real-Time Alarm")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        return resourceResponse(developerId ->activeAlarmService.delete(id, developerId));
    }

    @ApiOperation(value = "Querying Real-Time Alarms", notes = "Querying Real-Time Alarms")
    @GetMapping("/{id}")
    public Response<ActiveAlarm> getActiveAlarm(@PathVariable Long id) {
        return resourceResponse(developerId ->activeAlarmService.getById(id));
    }

    @ApiOperation(value = "List Querying real-time alarms", notes = "List Querying real-time alarms")
    @GetMapping("/list")
    public Response<List<ActiveAlarm>> list(@RequestParam(value = "alarmId", required = false) String alarmId,
                     @RequestParam(value = "alarmObjectUid", required = false) String alarmObjectUid,
                     @RequestParam(value = "alarmObjectName", required = false) String alarmObjectName,
                     @RequestParam(value = "alarmName", required = false) String alarmName,
                     @RequestParam(value = "alarmType", required = false) Integer alarmType,
                     @RequestParam(value = "alarmLevel", required = false) Integer alarmLevel,
                     @RequestParam(value = "startTime", required = false) String startTime,
                     @RequestParam(value = "endTime", required = false) String endTime,
                     @RequestParam(value = "neId", required = false) String neId,
                     @RequestParam(value = "neType",  required = false) String neType,
                     @RequestParam(value = "alarmDeviceType",  required = false) String alarmDeviceType,
                     @RequestParam(value = "source",  required = false) String source,
                     @RequestParam(value = "columnName", required = false) String columnName,
                     @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return resourceResponse(developerId ->activeAlarmService.listActiveAlarm(alarmId, alarmObjectUid, alarmObjectName, alarmName, alarmType,
                alarmLevel, startTime, endTime, neId, neType, alarmDeviceType, source, developerId, columnName, sortOrder));
    }

    @ApiOperation(value = "Paging query real-time alarms", notes = "Paging query real-time alarms")
    @GetMapping("/page")
    public Response<IPage<ActiveAlarm>> page(@RequestParam(value = "alarmId", required = false) String alarmId,
                                             @RequestParam(value = "alarmObjectUid", required = false) String alarmObjectUid,
                                             @RequestParam(value = "alarmObjectName", required = false) String alarmObjectName,
                                             @RequestParam(value = "alarmName", required = false) String alarmName,
                                             @RequestParam(value = "alarmType", required = false) Integer alarmType,
                                             @RequestParam(value = "alarmLevel", required = false) Integer alarmLevel,
                                             @RequestParam(value = "startTime", required = false) String startTime,
                                             @RequestParam(value = "endTime", required = false) String endTime,
                                             @RequestParam(value = "neId", required = false) String neId,
                                             @RequestParam(value = "neType", required = false) String neType,
                                             @RequestParam(value = "alarmDeviceType", required = false) String alarmDeviceType,
                                             @RequestParam(value = "source", required = false) String source,
                                             @RequestParam(value = "page", defaultValue = "1",required = false) int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "25", required = false) int pageSize,
                                             @RequestParam(value = "columnName", required = false) String columnName,
                                             @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return resourceResponse(developerId ->activeAlarmService.pageActiveAlarm(alarmId, alarmObjectUid, alarmObjectName, alarmName, alarmType,
                alarmLevel, developerId, startTime, endTime, neId, neType, alarmDeviceType, source, pageNum, pageSize, columnName, sortOrder));
    }

    @ApiOperation(value = "Manually Clearing an Alarm", notes = "Manually Clearing an Alarm")
    @PostMapping(value = "/cleared")
    public Response<Boolean> manualClearedAlarm(@RequestBody ActiveAlarm activeAlarm) {
        return resourceResponse(developerId ->alarmService.manualDeleteAlarm(activeAlarm,developerId));
    }

    @ApiOperation(value = "Query the number of real-time alarms based on alarmId", notes = "Query the number of real-time alarms based on alarmId")
    @GetMapping("/count")
    public Response<Integer> getActiveCountByAlarmId(@RequestParam(value = "alarmId", required = true) String alarmId){
        return resourceResponse(developerId ->activeAlarmService.getActiveCount(alarmId, developerId));
    }

    @ApiOperation(value = "Collect the real-time alarms on different labels", notes = "Collect the real-time alarms on different labels")
    @GetMapping("/statistics/{label}")
    public Response<List<ActiveAlarmStatisticsDTO>> getStatisticsInfo(@PathVariable(value = "label") String label){
        return resourceResponse(developerId ->activeAlarmService.getStatisticsInfo(developerId, label));
    }

    @ApiOperation(value = "Example Query the number of active alarms of each severity")
    @GetMapping(value = "/active/severity")
    public Response<List<ActiveAlarmSeverity>> severity(
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
        return resourceResponse(developerId -> activeAlarmSeverityService.countSeverity(neName, alarmNO, alarmObjectName, alarmName,alarmPvFlag, alarmLevel, alarmType, source, startTime,endTime,"active",developerId));
    }

    @ApiOperation(value = "Query the number of active alarms of each type")
    @GetMapping(value = "/active/alarmClass")
    Response<List<ActiveAlarmClass>> alarmClass(
            @RequestParam(value = "neName", required = false) String neName,
            @RequestParam(value = "alarmNO", required = false) String alarmNO,
            @RequestParam(value = "alarmName", required = false) String alarmName,
            @RequestParam(value = "alarmPvFlag", required = false) Integer alarmPvFlag,
            @RequestParam(value = "alarmLevel", required = false) Integer alarmLevel,
            @RequestParam(value = "alarmType", required = false) Integer alarmType,
            @RequestParam(value = "source", required = false) String source,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime, HttpServletRequest request){
        return resourceResponse(developerId ->
                activeAlarmClassService.countClass(neName, alarmNO,alarmName,alarmPvFlag, alarmLevel, alarmType, source,developerId, startTime,
                        endTime));
    }

    @ApiOperation(value = "Querying the total number of alarms")
    @GetMapping(value = "/active/alarmCount")
    Response<Long> alarmCount(
            @RequestParam(value = "alarmId", required = false) String alarmId){
        return resourceResponse(developerId ->
                activeAlarmClassService.countAlarm(alarmId,developerId,"active"));
    }

    @ApiOperation(value = "Filtering and pushing alarms")
    @PostMapping(value = "/push")
    public Response<Boolean> pushAlarm(@RequestBody Object object){
        return ResponseFactory.getSuccessData(alarmService.createRestActiveAlarm(object));
    }

}
