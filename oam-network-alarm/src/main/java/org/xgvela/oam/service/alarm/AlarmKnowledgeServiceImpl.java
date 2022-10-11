package org.xgvela.oam.service.alarm;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.alarm.active.AlarmKnowledge;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.mapper.alarm.active.AlarmKnowledgeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * AlarmKnowledgeServiceImpl Alarm knowledge base service implementation class
 * </p>
 */
@Service
public class AlarmKnowledgeServiceImpl extends ServiceImpl<AlarmKnowledgeMapper, AlarmKnowledge> implements IService<AlarmKnowledge> {

    /**
     * New Alarm Knowledge Base
     * @param alarmKnowledge
     * @return
     */
    public AlarmKnowledge create(AlarmKnowledge alarmKnowledge) {
        return this.save(alarmKnowledge) ? alarmKnowledge : null;
    }


    public boolean update(Long id, AlarmKnowledge alarmKnowledge) {
        AlarmKnowledge old = this.getById(id);
        if (ObjectUtils.isEmpty(old)) {
            throw new ServiceException("Failed to edit historical alarms. Procedure");
        }
        return this.updateById(alarmKnowledge);
    }


    public List<AlarmKnowledge> listAlarmKnowledge(String alarmId, String specificProblemId, String specificProblem,
                                                   String specificAnalyse, String specificUsername, String startTime,
                                                   String endTime, String developerId, String columnName, String sortOrder) {
        return this.baseMapper.selectForAlarmKnowledge(alarmId, specificProblemId, specificProblem, specificAnalyse,
                specificUsername, developerId, string2Date(startTime), string2Date(endTime),null, null, columnName, sortOrder);
    }


    public IPage<AlarmKnowledge> pageAlarmKnowledge(String alarmId, String specificProblemId, String specificProblem, String specificAnalyse, String specificUsername,
                                                    String developerId, String startTime, String endTime, int pageNum, int pageSize, String columnName, String sortOrder) {
        Page<AlarmKnowledge> knowledgePage = new Page<>(pageNum, pageSize);
        List<AlarmKnowledge> knowledgeList = this.baseMapper.selectForAlarmKnowledge(alarmId, specificProblemId,
                specificProblem, specificAnalyse, specificUsername, developerId, string2Date(startTime), string2Date(endTime),
                (knowledgePage.getCurrent() - 1) * knowledgePage.getSize(), knowledgePage.getSize(), columnName, sortOrder);
        knowledgePage.setRecords(knowledgeList);
        knowledgePage.setTotal(this.baseMapper.countForAlarmKnowledge(alarmId, specificProblemId, specificProblem,
                specificAnalyse, specificUsername, developerId, string2Date(startTime), string2Date(endTime)));
        return knowledgePage;
    }


    private Date string2Date(String time) {
        if (StringUtils.isNotBlank(time)) {
            try {
                return DateUtil.parse(time, AlarmKnowledge.ALARM_DATE_PARAM_PATTERN);
            } catch (Exception e) {
                throw new ServiceException("时间格式有误");
            }
        } else {
            return null;
        }
    }
}
