package com.gold.moni.webapi.helper.exception;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }
}