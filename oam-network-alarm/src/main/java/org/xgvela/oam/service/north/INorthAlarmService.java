package org.xgvela.oam.service.north;

import org.xgvela.oam.entity.AlarmVo;

import java.util.List;

public interface INorthAlarmService {
    List<AlarmVo> alarms(String apiVersion, String alarmIds);
}
