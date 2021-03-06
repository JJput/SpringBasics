package com.twj.spirngbasics.server.exception;


import com.twj.spirngbasics.server.dto.ResponseCode;

/**
 * 校验异常
 */
public class ValidatorException extends RuntimeException implements BaseException {

    private int code;
    private String message;

    public ValidatorException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ValidatorException(ResponseCode responseCode, String message) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage() + " : " + message;
    }

    public ValidatorException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
