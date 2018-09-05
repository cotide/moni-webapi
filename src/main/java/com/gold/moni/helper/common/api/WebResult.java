package com.gold.moni.helper.common.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 结果集
 */
public class WebResult {

    /**
     * 消息
     */
    @Getter
    private final String msg;

    /**
     * 是否请求成功
     */
    @Getter
    private Boolean isSuccess;

    /**
     * 构造函数
     * @param msg 消息
     * @param isSuccess 是否请求成功
     */
    public WebResult(String msg,Boolean isSuccess){

        this.msg = msg;
        this.isSuccess = isSuccess;
        if(isSuccess==true)
        {
            this.code = HttpStatusCode.OK;
        }
    }

    /**
     * 构造函数
     * @param msg 消息
     */
    public WebResult(String msg){
        this.msg = msg;
        this.isSuccess = true;
        this.code = HttpStatusCode.OK;
    }


    /**
     * 构造函数
     * @param msg 消息
     * @param statusCode Http 状态
     */
    public WebResult(String msg,HttpStatusCode statusCode){

        this.msg = msg;
        this.code = statusCode;
        if(statusCode== HttpStatusCode.OK)
        {
            this.isSuccess = true;
        } else{
            this.isSuccess =false;
        }
    }



    /**
     * Http 状态
     */
    @Getter
    @Setter
    private HttpStatusCode code;
}
