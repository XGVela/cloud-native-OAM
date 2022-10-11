package org.xgvela.oam.service.history;

import org.xgvela.oam.entity.alarm.history.ClearedAlarmSeverity;
import org.xgvela.oam.mapper.alarm.history.ClearedSeverityMapper;
import org.xgvela.oam.service.alarm.AlarmBaseServiceImpl;
import org.xgvela.oam.service.alarm.IAlarmBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("clearedAlarmSeverityService")
@Transactional(rollbackFor = Exception.class)
public class ClearedAlarmSeverityServiceImpl extends AlarmBaseServiceImpl<ClearedSeverityMapper, ClearedAlarmSeverity>
		implements IAlarmBaseService<ClearedAlarmSeverity> {
}