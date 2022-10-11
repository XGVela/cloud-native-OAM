package org.xgvela.oam.entity.alarm.analysis;

public enum FilterRelationEnum {

    and("AND","and"),
    or("OR","or");

    public String type;
    public String operator;

    FilterRelationEnum(String type,String operator){
        this.type = type;
        this.operator = operator;
    }


    private String getType(){
        return type;
    }

    private String getOperator(){
        return operator;
    }

    public static String getOperatorByType(String type) {
        if (type == null) {
            return null;
        }
        for (FilterRelationEnum result : FilterRelationEnum.values()) {
            if (result == null || result.getType() == null || !result.getType().equals(type)) {
                continue;
            }
            return result.getOperator();
        }
        return null;
    }
}
