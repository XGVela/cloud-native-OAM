package org.xgvela.oam.entity.response;

public enum ResultCode {
    SUCCESS(200, "Request is Succesful"),
    FAILED(500, "Request is Failed");

    private Integer code;
    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
