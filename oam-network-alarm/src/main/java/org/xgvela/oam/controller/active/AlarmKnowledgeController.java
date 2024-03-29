package org.xgvela.oam.controller.active;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.entity.alarm.active.AlarmKnowledge;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.alarm.AlarmKnowledgeServiceImpl;
import org.xgvela.oam.service.alarm.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * AlarmKnowledgeController
 * </p>
 */
@Api(tags = "Alarm Management - Alarm knowledge base")
@RestController
@RequestMapping("api/knowledge")
public class AlarmKnowledgeController {
    
    @Autowired
    private AlarmKnowledgeServiceImpl knowledgeService;
    /*@Autowired
    private UserServiceImpl userService;*/
    @Autowired
    private AlarmService alarmService;
    
    @ApiOperation("New Alarm Knowledge Base")
    @PostMapping
    public Response<AlarmKnowledge> create(@RequestBody AlarmKnowledge alarmKnowledge){
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        String token = currentUser.getPrincipal().toString();
        Object userId = alarmService.decodeToken(token, "userId");
        Object account = alarmService.decodeToken(token, "account");
        alarmKnowledge.setSpecificUserId(String.valueOf(userId));
        alarmKnowledge.setSpecificUsername(String.valueOf(account));
        return ResponseFactory.getSuccessData(knowledgeService.create(alarmKnowledge));
    }

    @ApiOperation(value = "Editing the Alarm Knowledge Base", notes = "Editing the Alarm Knowledge Base")
    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id,
                          @RequestBody AlarmKnowledge alarmKnowledge) {
        alarmKnowledge.setId(id);
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        String token = currentUser.getPrincipal().toString();
        Object userId = alarmService.decodeToken(token, "userId");
        Object account = alarmService.decodeToken(token, "account");
        alarmKnowledge.setSpecificUserId(String.valueOf(userId));
        alarmKnowledge.setSpecificUsername(String.valueOf(account));
        return ResponseFactory.getSuccessData(knowledgeService.update(id, alarmKnowledge));
    }

    @ApiOperation(value = "Deleting the Alarm Knowledge Base", notes = "Deleting the Alarm Knowledge Base")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        return ResponseFactory.getSuccessData(knowledgeService.removeById(id));
    }

    @ApiOperation(value = "Querying the Alarm Knowledge Base", notes = "Querying the Alarm Knowledge Base")
    @GetMapping("/{id}")
    public Response<AlarmKnowledge> getHistoryAlarm(@PathVariable Long id) {
        return ResponseFactory.getSuccessData(knowledgeService.getById(id));
    }

    @ApiOperation(value = "List Queries the alarm knowledge base", notes = "List Queries the alarm knowledge base")
    @GetMapping("/list")
    public Response<List<AlarmKnowledge>> list(@RequestParam(value = "alarmId", required = false) String alarmId,
                                     @RequestParam(value = "specificProblemId", required = false) String specificProblemId,
                                     @RequestParam(value = "specificProblem", required = false) String specificProblem,
                                     @RequestParam(value = "specificAnalyse", required = false) String specificAnalyse,
                                     @RequestParam(value = "specificUsername", required = false) String specificUsername,
                                     @RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime,
                                     @RequestParam(value = "developerId", required = false) String developerId,
                                     @RequestParam(value = "columnName", required = false) String columnName,
                                     @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        
        return ResponseFactory.getSuccessData(knowledgeService.listAlarmKnowledge(alarmId, specificProblemId, specificProblem, specificAnalyse, specificUsername,
                startTime, endTime, developerId, columnName, sortOrder));
    }

    @ApiOperation(value = "Page query the alarm knowledge base", notes = "Page query the alarm knowledge base")
    @GetMapping("/page")
    public Response<IPage<AlarmKnowledge>> page(@RequestParam(value = "alarmId", required = false) String alarmId,
                                                @RequestParam(value = "specificProblemId", required = false) String specificProblemId,
                                                @RequestParam(value = "specificProblem", required = false) String specificProblem,
                                                @RequestParam(value = "specificAnalyse", required = false) String specificAnalyse,
                                                @RequestParam(value = "specificUsername", required = false) String specificUsername,
                                                @RequestParam(value = "startTime", required = false) String startTime,
                                                @RequestParam(value = "endTime", required = false) String endTime,
                                                @RequestParam(value = "developerId", required = false) String developerId,
                                                @RequestParam(value = "page", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "25") int pageSize,
                                                @RequestParam(value = "columnName", required = false) String columnName,
                                                @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return ResponseFactory.getSuccessData(knowledgeService.pageAlarmKnowledge(alarmId, specificProblemId, specificProblem, specificAnalyse, specificUsername,
                developerId, startTime, endTime, pageNum, pageSize, columnName, sortOrder));
    }
}
