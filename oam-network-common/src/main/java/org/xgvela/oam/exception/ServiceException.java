package org.xgvela.oam.exception;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 3583566093089790852L;
    private Integer code;

    public ServiceException() {
    }

    public ServiceException(Integer code) {
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
