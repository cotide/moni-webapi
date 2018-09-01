package com.gold.moni.helper.exception;

/**
 * 权限异常
 */
public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException(String msg) {
        super(msg);
    }
}
