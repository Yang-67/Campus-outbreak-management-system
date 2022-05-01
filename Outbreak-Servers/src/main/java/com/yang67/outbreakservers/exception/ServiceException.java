package com.yang67.outbreakservers.exception;

/**
 * 自定义异常
 */

public class ServiceException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
