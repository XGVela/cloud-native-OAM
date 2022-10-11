package org.xgvela.oam.service.alarm;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.statistics.ActiveAlarmStatisticsDTO;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.mapper.alarm.active.ActiveAlarmMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * ActiveAlarmServiceImpl
 * </p>
 */
@Service
@Slf4j
public class ActiveAlarmServiceImpl extends ServiceImpl<ActiveAlarmMapper, ActiveAlarm> implements IService<ActiveAlarm> {

    private static final Integer ALARM_CONFIRM = 1;

    /**
     * @param activeAlarm
     * @return ActiveAlarm
     */
    public ActiveAlarm create(ActiveAlarm activeAlarm, String developerId) {
        activeAlarm.setDeveloperId(developerId);
        return this.save(activeAlarm) ? activeAlarm : null;
    }

    /**
     * @param id
     * @param developerId
     * @param activeAlarm
     * @return boolean
     */
    public Boolean update(Long id, String developerId, ActiveAlarm activeAlarm) {
        ActiveAlarm alarm = this.getById(id);
        if (ObjectUtils.isEmpty(alarm)) {
            throw new ServiceException("Failed to edit real-time alarms. Procedure");
        }
        activeAlarm.setDeveloperId(developerId);
        LambdaUpdateWrapper<ActiveAlarm> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(ActiveAlarm::getId, id)
                .eq(StringUtils.isNotBlank(developerId),ActiveAlarm::getDeveloperId, developerId);
        return this.update(activeAlarm, updateWrapper);
    }

    public Boolean confirm(Long id, String developerId, Integer ackState, String userId, String userName) {
        ActiveAlarm activeAlarm = this.getById(id);
        if (ObjectUtils.isEmpty(activeAlarm)) {
            throw new ServiceException("Alarm confirmation failure");
        }
        if (ObjectUtils.isNotEmpty(activeAlarm.getAlarmConfirmUserid()) && !userId.equals(activeAlarm.getAlarmConfirmUserid()) && !"admin".equals(userName)) {
            throw new ServiceException("Alarm confirmation failure");
        }
        activeAlarm.setAlarmConfirmUserid(userId);
        activeAlarm.setAlarmConfirmUsername(userName);
        activeAlarm.setAlarmConfirmTime(new Date());
        activeAlarm.setAckState(ackState);
        if (ALARM_CONFIRM.equals(ackState)) {
            return this.update(id, developerId, activeAlarm);
        } else {
            return this.update(Wrappers.<ActiveAlarm>lambdaUpdate()
                    .eq(ActiveAlarm::getId, id)
                    .eq(StringUtils.isNotBlank(developerId),ActiveAlarm::getDeveloperId, developerId)
                    .set(ActiveAlarm::getAckState, ackState)
                    .set(ActiveAlarm::getAlarmConfirmUserid, null)
                    .set(ActiveAlarm::getAlarmConfirmUsername, null)
                    .set(ActiveAlarm::getAlarmConfirmTime, null));
        }
    }

    /**
     * Delete real-time alarms based on the tenant
     * @param id
     * @param developerId
     * @return
     */
    public Boolean delete(Long id, String developerId) {
        LambdaQueryWrapper<ActiveAlarm> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ActiveAlarm::getId, id).eq(StringUtils.isNotBlank(developerId),ActiveAlarm::getDeveloperId, developerId);
        return this.remove(queryWrapper);
    }

    /**
     * List Querying real-time alarms
     */
    public List<ActiveAlarm> listActiveAlarm(String alarmId, String alarmObjectUid, String alarmObjectName, String alarmName, Integer alarmType, Integer alarmLevel,
                                             String startTime, String endTime, String neId, String neType, String alarmDeviceType, String source, String developerId, String columnName, String sortOrder) {

        return this.list(buildQueryWrapper(alarmId, alarmObjectUid, alarmObjectName, alarmName, alarmType, alarmLevel, startTime, endTime, neId, neType, alarmDeviceType, source, developerId, columnName, sortOrder));
    }

    /**
     * Paging query real-time alarms
     */
    public IPage<ActiveAlarm> pageActiveAlarm(String alarmId, String alarmObjectUid, String alarmObjectName, String alarmName, Integer alarmType, Integer alarmLevel,
                                              String developerId, String startTime, String endTime, String neId, String neType, String alarmDeviceType, String source, int pageNum, int pageSize, String columnName, String sortOrder) {

        return this.page(new Page<>(pageNum, pageSize), buildQueryWrapper(alarmId, alarmObjectUid, alarmObjectName, alarmName, alarmType, alarmLevel, startTime, endTime, neId, neType, alarmDeviceType, source, developerId, columnName, sortOrder));

    }

    private LambdaQueryWrapper<ActiveAlarm> buildQueryWrapper(String alarmId, String alarmObjectUid, String alarmObjectName, String alarmName, Integer alarmType, Integer alarmLevel, String startTime, String endTime, String neId, String neType, String alarmDeviceType, String source, String developerId, String columnName, String sortOrder) {
        LambdaQueryWrapper<ActiveAlarm> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(alarmId), ActiveAlarm::getAlarmId, alarmId)
                .eq(StringUtils.isNotEmpty(alarmObjectUid), ActiveAlarm::getAlarmObjectUid, alarmObjectUid)
                .like(StringUtils.isNotEmpty(alarmObjectName), ActiveAlarm::getAlarmObjectName, alarmObjectName)
                .like(StringUtils.isNotEmpty(alarmName), ActiveAlarm::getAlarmName, alarmName)
                .eq(null != alarmType, ActiveAlarm::getAlarmType, alarmType)
                .eq(null != alarmLevel, ActiveAlarm::getAlarmLevel, alarmLevel)
                .eq(StringUtils.isNotEmpty(neId), ActiveAlarm::getNeId, neId)
                .eq(StringUtils.isNotEmpty(neType), ActiveAlarm::getNeType, neType)
                .eq(StringUtils.isNotEmpty(alarmDeviceType), ActiveAlarm::getAlarmDeviceType, alarmDeviceType)
                .eq(StringUtils.isNotEmpty(source), ActiveAlarm::getSource, source)
                .eq(StringUtils.isNotEmpty(developerId), ActiveAlarm::getDeveloperId, developerId);
        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            try {
                Date startDate = DateUtil.parse(startTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
                Date endDate = DateUtil.parse(endTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
                queryWrapper.ge(ActiveAlarm::getAlarmEventTime, startDate);
                queryWrapper.le(ActiveAlarm::getAlarmEventTime, endDate);
            } catch (Exception e) {
                throw new ServiceException("Wrong time format");
            }
        }
        String lastSql = String.format("ORDER BY %s %s", columnName, sortOrder);
        if (StringUtils.isNotEmpty(columnName)) {
            queryWrapper.last(lastSql);
        } else {
            queryWrapper.orderByDesc(ActiveAlarm::getAlarmEventTime);
        }
        return queryWrapper;
    }
    
    public int getActiveCount(String alarmId, String developerId){
        QueryWrapper<ActiveAlarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("alarm_id", alarmId);
        queryWrapper.eq("merge_flag", 0);
        queryWrapper.eq(StringUtils.isNotBlank(developerId), "developer_id", developerId);
        
        return count(queryWrapper);
    }

    /**
     * Collect the number of real-time alarms based on the fields in the dictionary
     * @param developerId
     * @param label
     * @return
     */
    public List<ActiveAlarmStatisticsDTO> getStatisticsInfo(String developerId, String label) {
        List<ActiveAlarmStatisticsDTO> statisticsList = this.baseMapper.getCountByLabel(developerId, label);
        ActiveAlarmStatisticsDTO statisticsDTO = ActiveAlarmStatisticsDTO.builder()
                .type("total count")
                .count(count(Wrappers.<ActiveAlarm>lambdaQuery()
                        .eq(StringUtils.isNotBlank(developerId), ActiveAlarm::getDeveloperId, developerId)))
                .build();
        statisticsList.add(statisticsDTO);
        return statisticsList;
    }

    public boolean deleteActiveAlarm(Long id, String developerId){
        return  remove(new LambdaQueryWrapper<ActiveAlarm>()
                .eq(ActiveAlarm::getId, id)
                .eq(StringUtils.isNotBlank(developerId),ActiveAlarm::getDeveloperId, developerId));
    }

}
