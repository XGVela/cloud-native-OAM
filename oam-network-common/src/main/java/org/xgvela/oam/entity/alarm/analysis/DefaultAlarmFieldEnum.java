package org.xgvela.oam.entity.alarm.analysis;

public enum DefaultAlarmFieldEnum {


    alarm_name("alarmName"),
    alarm_type("alarmType"),
    alarm_level("alarmLevel"),
    alarm_object("alarmObject"),
    alarm_location_info("alarmLocationInfo"),
    alarm_detail("alarmDetail"),
    specific_problem("specificProblem"),
    ne_type("neType"),
    alarm_object_name("alarmObjectName"),
    alarm_object_type("alarmObjectType"),
    alarm_add_info("alarmAddInfo"),
    alarm_event_title("alarmEventTitle"),
    alarm_device_type("alarmDeviceType"),
    ne_uname("neUname");

    private String name;

    DefaultAlarmFieldEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
