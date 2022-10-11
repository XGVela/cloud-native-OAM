package org.xgvela.oam.entity.alarm.analysis;

public enum ValueTypeEnum {
    alarm("alarm"),
    fixed("fixed");

    private String name;

    ValueTypeEnum(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
