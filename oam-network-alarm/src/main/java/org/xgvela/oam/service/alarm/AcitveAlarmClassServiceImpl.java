package org.xgvela.oam.service.alarm;

import org.xgvela.oam.entity.alarm.active.ActiveAlarmClass;
import org.xgvela.oam.mapper.alarm.active.ActiveClassMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AcitveAlarmClassServiceImpl extends AlarmBaseServiceImpl<ActiveClassMapper, ActiveAlarmClass>
implements IAlarmBaseService<ActiveAlarmClass> {

}