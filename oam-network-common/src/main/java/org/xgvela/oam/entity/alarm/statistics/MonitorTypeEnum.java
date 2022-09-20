package org.xgvela.oam.entity.alarm.statistics;
/**
 * @author maopu
 */
public enum MonitorTypeEnum {

    MSG_SEND_UP("msgSendUp"),
    MSG_SEND_DOWN("msgSendDown"),
    MSG_SEND_UP_FAILED("msgSendUpFailed"),
    MSG_SEND_DOWN_FAILED("msgSendDownFailed"),
    MSG_SHADOW_UPDATE("msgShadowUpdate"),
    MSG_SHADOW_UPDATE_FAILED("msgShadowUpdateFailed"),
    MSG_RULE_ENGINE_HIT("msgRuleEngineHit"),
    MSG_RULE_ENGINE_FORWARD("msgRuleEngineForward"),
    MSG_ALARM_COUNT("msgAlarmCount");

    private String type;


    MonitorTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
