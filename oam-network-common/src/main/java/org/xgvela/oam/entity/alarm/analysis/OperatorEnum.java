package org.xgvela.oam.entity.alarm.analysis;

public enum OperatorEnum {

    greater_than(">",">"),
    less_than("<","<"),
    equal_sign("==","=="),
    unEqual_sign("!=","!="),
    greater_equal(">=",">="),
    less_equal("<=","<="),
    like("contains","contains"),
    in("in","=~");

    public String type;
    public String operator;

    OperatorEnum(String type,String operator){
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
        for (OperatorEnum result : OperatorEnum.values()) {
            if (result == null || result.getType() == null || !result.getType().equals(type)) {
                continue;
            }
            return result.getOperator();
        }
        return null;
    }

}
