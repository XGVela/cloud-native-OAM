package org.xgvela.oam.service.alarm;

import org.xgvela.oam.entity.alarm.active.ActiveAlarmSeverity;
import org.xgvela.oam.mapper.alarm.active.ActiveSeverityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class AcitveAlarmSeverityServiceImpl extends AlarmBaseServiceImpl<ActiveSeverityMapper, ActiveAlarmSeverity>
		implements IAlarmBaseService<ActiveAlarmSeverity> {
}
