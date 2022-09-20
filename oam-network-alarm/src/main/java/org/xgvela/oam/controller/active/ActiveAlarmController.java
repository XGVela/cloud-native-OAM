package org.xgvela.oam.controller.active;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import com.inspur.cnet.security.entity.Developer;
import com.inspur.cnet.security.service.DeveloperServiceImpl;
import com.inspur.cnet.security.service.UserServiceImpl;
import org.xgvela.oam.controller.BaseResourceController;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.active.ActiveAlarmClass;
import org.xgvela.oam.entity.alarm.active.ActiveAlarmSeverity;
import org.xgvela.oam.entity.alarm.statistics.ActiveAlarmStatisticsDTO;
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
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * ActiveAlarmController
 * </p>
 */
@Api(tags = "alarm manage-active alarm")
@RestController
@RequestMapping("api/active-alarms")
public class ActiveAlarmController extends BaseResourceController {

    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private DeveloperServiceImpl developerService;
    @Autowired
    private AcitveAlarmSeverityServiceImpl activeAlarmSeverityService;
    @Autowired
    private AcitveAlarmClassServiceImpl activeAlarmClassService;


    @ApiOperation(value = "create", notes = "create")
    @PostMapping
    public Response<ActiveAlarm> create(@RequestBody ActiveAlarm activeAlarm) {
        return resourceResponse(developerId ->activeAlarmService.create(activeAlarm,developerId));
    }

    @ApiOperation(value = "update", notes = "update")
    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id,
                          @RequestBody ActiveAlarm activeAlarm) {
        activeAlarm.setId(id);
        return resourceResponse(developerId ->activeAlarmService.update(id, developerId, activeAlarm));
    }

    @ApiOperation(value = "confirm", notes = "confirm")
    @PutMapping("/{id}/confirm")
    public Response<Boolean> confirm(@PathVariable Long id,
                           @RequestParam(value = "ackState") Integer ackState) {
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        String token = currentUser.getPrincipal().toString();
        Object userId = alarmService.decodeToken(token, "userId");
        Object account = alarmService.decodeToken(token, "account");
        return resourceResponse(developerId ->activeAlarmService.confirm(id, developerId, ackState, String.valueOf(userId), String.valueOf(account)));
    }

    @ApiOperation(value = "delete", notes = "delete")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        return resourceResponse(developerId ->activeAlarmService.delete(id, developerId));
    }

    @ApiOperation(value = "getActiveAlarm", notes = "getActiveAlarm")
    @GetMapping("/{id}")
    public Response<ActiveAlarm> getActiveAlarm(@PathVariable Long id) {
        return resourceResponse(developerId ->activeAlarmService.getById(id));
    }

    @ApiOperation(value = "list", notes = "list")
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

    @ApiOperation(value = "page", notes = "page")
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

    @ApiOperation(value = "manualClearedAlarm", notes = "manualClearedAlarm")
    @PostMapping(value = "/cleared")
    public Response<Boolean> manualClearedAlarm(@RequestBody ActiveAlarm activeAlarm) {
        return resourceResponse(developerId ->alarmService.manualDeleteAlarm(activeAlarm,developerId));
    }

    @ApiOperation(value = "getActiveCountByAlarmId", notes = "getActiveCountByAlarmId")
    @GetMapping("/count")
    public Response<Integer> getActiveCountByAlarmId(@RequestParam(value = "alarmId", required = true) String alarmId){
        return resourceResponse(developerId ->activeAlarmService.getActiveCount(alarmId, developerId));
    }

    @ApiOperation(value = "getStatisticsInfo", notes = "getStatisticsInfo")
    @GetMapping("/statistics/{label}")
    public Response<List<ActiveAlarmStatisticsDTO>> getStatisticsInfo(@PathVariable(value = "label") String label){
        return resourceResponse(developerId ->activeAlarmService.getStatisticsInfo(developerId, label));
    }

    @ApiOperation(value = "severity")
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

    @ApiOperation(value = "alarmClass")
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

    @ApiOperation(value = "alarmCount")
    @GetMapping(value = "/active/alarmCount")
    Response<Long> alarmCount(
            @RequestParam(value = "alarmId", required = false) String alarmId){
        return resourceResponse(developerId ->
                activeAlarmClassService.countAlarm(alarmId,developerId,"active"));
    }

    @ApiOperation(value = "pushAlarm")
    @PostMapping(value = "/push")
    public Response<Boolean> pushAlarm(@RequestBody Object object){
        return ResponseFactory.getSuccessData(alarmService.createRestActiveAlarm(object));
    }

}
