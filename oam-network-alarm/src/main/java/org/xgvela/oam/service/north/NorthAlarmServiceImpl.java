package org.xgvela.oam.service.north;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.AlarmVo;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.history.HistoryAlarm;
import org.xgvela.oam.service.alarm.ActiveAlarmServiceImpl;
import org.xgvela.oam.service.history.HistoryAlarmServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NorthAlarmServiceImpl implements INorthAlarmService {
    private static final String SPLIT = ",";

    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;


    @Override
    public List<AlarmVo> alarms(String apiVersion, String alarmIds) {
        List<AlarmVo> alarmDtoList = new ArrayList<>();

        LambdaQueryWrapper<ActiveAlarm> activeAlarmQueryWrapper = new LambdaQueryWrapper<ActiveAlarm>();
        LambdaQueryWrapper<HistoryAlarm> historyAlarmQueryWrapper = new LambdaQueryWrapper<HistoryAlarm>();
        if (StringUtils.isNoneEmpty(alarmIds)) {
            List<String> alarmIdList = Arrays.asList(alarmIds.split(SPLIT));
            activeAlarmQueryWrapper.in(ActiveAlarm::getAlarmId, alarmIdList);
            historyAlarmQueryWrapper.in(HistoryAlarm::getAlarmId, alarmIdList);
        }

        List<ActiveAlarm> activeAlarmList = activeAlarmService.list(activeAlarmQueryWrapper);
        if (ObjectUtils.isNotEmpty(activeAlarmList)) {
            activeAlarmList.forEach(activeAlarm -> {
                AlarmVo active = new AlarmVo();
                BeanUtils.copyProperties(activeAlarm, active);
                alarmDtoList.add(active);
            });
        }

        List<HistoryAlarm> historyAlarmList = historyAlarmService.list(historyAlarmQueryWrapper);
        if (ObjectUtils.isNotEmpty(historyAlarmList)) {
            historyAlarmList.forEach(historyAlarm -> {
                AlarmVo history = new AlarmVo();
                BeanUtils.copyProperties(historyAlarm, history);
                alarmDtoList.add(history);
            });
        }
        return alarmDtoList;
    }
}
