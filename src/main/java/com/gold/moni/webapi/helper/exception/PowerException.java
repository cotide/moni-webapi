package com.gold.moni.webapi.helper.exception;

/**
 * 权限异常
 * @author cotide
 */
public class PowerException  extends RuntimeException {

    public  PowerException(String error)
    {
        super(error);
    }

    public  PowerException()
    {
        super("Access Permission Denied");
    }
}
