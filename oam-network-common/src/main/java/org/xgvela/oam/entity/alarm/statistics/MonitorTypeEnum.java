package org.xgvela.oam.entity.alarm.statistics;

public enum MonitorTypeEnum {
    /*** Monitors message types
     * MSG_SEND_UP: indicates an uplink message
     * MSG_SEND_DOWN: indicates a downlink message
     * MSG_SEND_UP_FAILED: indicates an uplink failure message
     * MSG_SEND_DOWN_FAILED: indicates a downlink failure message
     * MSG_SHADOW_UPDATE: device shadow update message
     * MSG_SHADOW_UPDATE_FAILED: indicates that the device shadow update failed
     * MSG_RULE_ENGINE_HIT: indicates the matching message data of the linkage rule
     * MSG_RULE_ENGINE_FORWARD: indicates the number of messages forwarded by the rule engine
     * */

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