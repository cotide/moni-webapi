package com.gold.moni.webapi.helper.exception;

/***
 * 数据库异常
 */
public class DbErrorException extends RuntimeException {

    public  DbErrorException(String error)
    {
        super(error);
    }
}
