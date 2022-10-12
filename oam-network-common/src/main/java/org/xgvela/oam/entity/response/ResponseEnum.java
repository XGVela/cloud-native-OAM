package org.xgvela.oam.entity.response;

public enum ResponseEnum {
    success(200, "操作成功"),
    closure(301, "被封禁"),
    error(400, "很抱歉,系统发生故障"),
    please_login(401, "没有权限"),
    forbidden(403, "没有足够的权限"),
    incomplete_develoer_info(402, "开发者信息尚未完善"),
    obj_not_exist(404, "对象不存在"),
    param_error(414, "参数错误"),
    failure(500, "操作失败");

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
