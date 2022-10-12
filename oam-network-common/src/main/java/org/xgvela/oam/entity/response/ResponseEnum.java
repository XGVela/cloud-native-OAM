package org.xgvela.oam.entity.response;

public enum ResponseEnum {
    success(200, "operation successful "),
    closure(301, "closed "),
    error(400, "Sorry, the system is out of order "),
    please_login(401, "no permission "),
    forbidden(403, "Do not have enough permission "),
    incomplete_develoer_info(402, "developer info not yet developed "),
    obj_not_exist(404, "Object does not exist "),
    param_error(414, "parameter error "),
    failure(500, "fail to operate ");

    private int code;
    private String message;

    private ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
