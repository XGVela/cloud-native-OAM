package org.xgvela.oam.entity.alarm.notice;

import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransmitEventVo {
    private String eventId;
    private ActiveAlarm content;
}
