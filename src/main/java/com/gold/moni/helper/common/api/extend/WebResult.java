package com.gold.moni.helper.common.api.extend;

import com.gold.moni.helper.common.api.HttpStatusCode;
import lombok.Getter;
import lombok.Setter;

public  class WebResult<T> extends com.gold.moni.helper.common.api.WebResult
{
    @Getter
    private T data;

    public WebResult(
            T data,
            String msg,
            boolean isSuccess){
        super(msg,isSuccess);
        this.data = data;
    }


    public WebResult(
            T data,
            boolean isSuccess){
        super("",isSuccess);
        this.data = data;
    }


    public WebResult(
            T data,
            HttpStatusCode statusCode){
        super("",statusCode);
        this.data = data;
    }

}
