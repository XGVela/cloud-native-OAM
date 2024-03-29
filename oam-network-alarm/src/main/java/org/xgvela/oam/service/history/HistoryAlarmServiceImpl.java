package org.xgvela.oam.service.history;

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
import org.xgvela.oam.entity.alarm.history.HistoryAlarm;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.mapper.alarm.history.HistoryAlarmMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * HistoryAlarmServiceImpl
 * </p>
 */
@Service
public class HistoryAlarmServiceImpl extends ServiceImpl<HistoryAlarmMapper, HistoryAlarm> implements IService<HistoryAlarm> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HistoryAlarm create(HistoryAlarm historyAlarm, String developerId) {
        historyAlarm.setDeveloperId(developerId);
        return this.save(historyAlarm) ? historyAlarm : null;
    }

    public Boolean update(Long id, String developerId, HistoryAlarm historyAlarm) {
        HistoryAlarm old = this.getById(id);
        if (ObjectUtils.isEmpty(old)) {
            throw new ServiceException("Failed to edit historical alarms. Procedure");
        }
        historyAlarm.setDeveloperId(developerId);
        if (old.getAlarmName().equals(historyAlarm.getAlarmName())) {
            int count = this.count(new LambdaQueryWrapper<HistoryAlarm>()
                    .eq(HistoryAlarm::getAlarmName, historyAlarm.getAlarmName())
                    .eq(HistoryAlarm::getDeveloperId, historyAlarm.getDeveloperId()));
            if (count > 0) {
                throw new ServiceException("A real-time alarm of this name has been generate");
            }
        }
        LambdaUpdateWrapper<HistoryAlarm> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(HistoryAlarm::getId, id).eq(HistoryAlarm::getDeveloperId, developerId);
        return this.update(historyAlarm, updateWrapper);
    }

    public boolean delete(Long id, String developerId) {
        LambdaQueryWrapper<HistoryAlarm> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(HistoryAlarm::getId, id).eq(HistoryAlarm::getDeveloperId, developerId);
        return this.remove(queryWrapper);
    }

    public List<HistoryAlarm> listHistoryAlarm(String alarmId, String alarmNO, String alarmObjectUid, String alarmObjectName, String alarmName, Integer alarmPvFlag, Integer alarmType, Integer alarmLevel, String startTime, String endTime, String developerId, String columnName, String sortOrder) {
        return this.list(buildQueryWrapper(alarmId, alarmNO, alarmObjectUid, alarmObjectName, alarmName, alarmPvFlag, alarmType, alarmLevel, startTime, endTime, developerId, columnName, sortOrder));
    }


    public IPage<HistoryAlarm> pageHistoryAlarm(String alarmId, String alarmNO, String alarmObjectUid, String alarmObjectName, String alarmName, Integer alarmPvFlag, Integer alarmType, Integer alarmLevel,
                                                String developerId, String startTime, String endTime, int pageNum, int pageSize, String columnName, String sortOrder) {
        return this.page(new Page<>(pageNum, pageSize), buildQueryWrapper(alarmId, alarmNO, alarmObjectUid, alarmObjectName, alarmName, alarmPvFlag, alarmType, alarmLevel, startTime, endTime, developerId, columnName, sortOrder));
    }

    private LambdaQueryWrapper<HistoryAlarm> buildQueryWrapper(String alarmId, String alarmNO, String alarmObjectUid, String alarmObjectName, String alarmName, Integer alarmPvFlag, Integer alarmType, Integer alarmLevel, String startTime, String endTime, String developerId, String columnName, String sortOrder) {
        LambdaQueryWrapper<HistoryAlarm> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(alarmId), HistoryAlarm::getAlarmId, alarmId)
                .eq(StringUtils.isNotEmpty(alarmObjectUid), HistoryAlarm::getAlarmObjectUid, alarmObjectUid)
                .like(StringUtils.isNotEmpty(alarmObjectName), HistoryAlarm::getAlarmObjectName, alarmObjectName)
                .like(StringUtils.isNotEmpty(alarmName), HistoryAlarm::getAlarmName, alarmName)
                .like(StringUtils.isNotEmpty(alarmNO), HistoryAlarm::getAlarmNO, sqlEncode(alarmNO))
                .eq(null != alarmPvFlag, HistoryAlarm::getAlarmPvFlag, alarmPvFlag)
                .eq(null != alarmType, HistoryAlarm::getAlarmType, alarmType)
                .eq(null != alarmLevel, HistoryAlarm::getAlarmLevel, alarmLevel)
                .eq(StringUtils.isNotEmpty(developerId), HistoryAlarm::getDeveloperId, developerId);
        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            try {
                Date startDate = DateUtil.parse(startTime, HistoryAlarm.ALARM_DATE_PARAM_PATTERN);
                Date endDate = DateUtil.parse(endTime, HistoryAlarm.ALARM_DATE_PARAM_PATTERN);
                queryWrapper.ge(HistoryAlarm::getAlarmEventTime, startDate);
                queryWrapper.le(HistoryAlarm::getAlarmEventTime, endDate);
            } catch (Exception e) {
                throw new ServiceException("e");
            }
        }
        String lastSql = String.format("ORDER BY %s %s", columnName, sortOrder);
        if (StringUtils.isNotEmpty(columnName)) {
            queryWrapper.last(lastSql);
        } else {
            queryWrapper.orderByDesc(HistoryAlarm::getAlarmEventTime);
        }
        return queryWrapper;
    }

    private String sqlEncode(String str) {
        if("[".equals(str)) {
            return "[[]";
        }else if("_".equals(str)) {
            return "[_]";
        }else if("%".equals(str)) {
            return "[%]";
        }
        return str;
    }

    public Boolean deleteByDate(long date){
        QueryWrapper<HistoryAlarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("alarm_cleared_time", new Date(date));
        return this.remove(queryWrapper);
    }

    private Date timeStamp2Date(long timeLong) {
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
