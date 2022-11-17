package org.xgvela.oam.service.north;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.AlarmVo;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.history.HistoryAlarm;
import org.xgvela.oam.entity.auth.OamUsers;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.service.alarm.ActiveAlarmServiceImpl;
import org.xgvela.oam.service.auth.IOamUsersService;
import org.xgvela.oam.service.history.HistoryAlarmServiceImpl;
import org.xgvela.oam.utils.JwtUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class NorthAlarmServiceImpl implements INorthAlarmService {
    private static final String SPLIT = ",";

    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;
    @Autowired
    private IOamUsersService oamUsersService;
    @Autowired
    private OamVnfMapper oamVnfMapper;

    @Override
    public List<AlarmVo> alarms(String apiVersion, String alarmIds, String startTime, String endTime, String neId, String neType, String alarmLevel, String alarmTitle) {
        List<AlarmVo> alarmDtoList = new ArrayList<>();
        LambdaQueryWrapper<ActiveAlarm> activeAlarmQueryWrapper = new LambdaQueryWrapper<ActiveAlarm>();
        LambdaQueryWrapper<HistoryAlarm> historyAlarmQueryWrapper = new LambdaQueryWrapper<HistoryAlarm>();
        /*if (StringUtils.isNoneEmpty(alarmIds)) {
            List<String> alarmIdList = Arrays.asList(alarmIds.split(SPLIT));
            activeAlarmQueryWrapper.in(ActiveAlarm::getAlarmId, alarmIdList);
            historyAlarmQueryWrapper.in(HistoryAlarm::getAlarmId, alarmIdList);
        }*/

        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            try {
                Date startDate = DateUtil.parse(startTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
                Date endDate = DateUtil.parse(endTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
                activeAlarmQueryWrapper.ge(ActiveAlarm::getAlarmEventTime, startDate);
                activeAlarmQueryWrapper.le(ActiveAlarm::getAlarmEventTime, endDate);

                historyAlarmQueryWrapper.ge(HistoryAlarm::getAlarmEventTime, startDate);
                historyAlarmQueryWrapper.le(HistoryAlarm::getAlarmEventTime, endDate);
            } catch (Exception e) {
                throw new ServiceException("e");
            }
        }

        List<String> neIds = getNeIdsBytoken();
        if (Objects.nonNull(neIds)) {
            activeAlarmQueryWrapper.in(ActiveAlarm::getNeId, neIds);

            historyAlarmQueryWrapper.in(HistoryAlarm::getNeId, neIds);
        }

        activeAlarmQueryWrapper.eq(StringUtils.isNotEmpty(neId), ActiveAlarm::getNeId, neId)
                .eq(StringUtils.isNotEmpty(neType), ActiveAlarm::getNeType, neType)
                .eq(StringUtils.isNotEmpty(alarmLevel), ActiveAlarm::getAlarmLevel, Integer.parseInt(alarmLevel))
                .like(StringUtils.isNotEmpty(alarmTitle), ActiveAlarm::getAlarmName, alarmTitle);

        historyAlarmQueryWrapper.eq(StringUtils.isNotEmpty(neId), HistoryAlarm::getNeId, neId)
                .eq(StringUtils.isNotEmpty(neType), HistoryAlarm::getNeType, neType)
                .eq(StringUtils.isNotEmpty(alarmLevel), HistoryAlarm::getAlarmLevel, Integer.parseInt(alarmLevel))
                .like(StringUtils.isNotEmpty(alarmTitle), HistoryAlarm::getAlarmName, alarmTitle);


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

    private List<String> getNeIdsBytoken() {
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        if (currentUser != null) {

            OamUsers oamUser = oamUsersService.getOne(new LambdaQueryWrapper<OamUsers>().eq(OamUsers::getUserId, JwtUtils.getUserId(currentUser.getPrincipal().toString())));
            if (Objects.nonNull(oamUser)) {
                List<String> neIds = oamVnfMapper.selectList(new LambdaQueryWrapper<OamVnf>().eq(OamVnf::getSystemId, oamUser.getSystemId())).stream().map(OamVnf::getNeId).distinct().collect(Collectors.toList());
                if (Objects.nonNull(neIds) && neIds.size() > 0) {
                    return neIds;
                }
            }
        }
        return null;
    }
}
