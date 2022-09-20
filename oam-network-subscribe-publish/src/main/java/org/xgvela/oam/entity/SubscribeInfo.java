package org.xgvela.oam.entity;

import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SubscribeInfo implements Serializable {
    private String neId;
    private List<String> register;
    private List<ActiveAlarm> alarm;
    private List<String> perf;
}
