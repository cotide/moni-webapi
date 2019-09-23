package com.gold.moni.webapi.helper.common.api;

import com.alibaba.fastjson.annotation.JSONType;
import com.gold.moni.webapi.helper.common.inter.IValuedEnum;

/**
 * Http 状态
 */
@JSONType(serializeEnumAsJavaBean = true)
public  enum HttpStatusCode implements IValuedEnum
{

    OK(200,"OK"),
    Unauthorized(401,"非法权限"),
    RequestDataError(510,"请求参数异常"),
    BusinessError(511,"业务异常"),
    InternalServerError(500,"服务器异常");


    /**
     * 值
     */
    private int value;

    /**
     * 描述
     */
    private String remark;


    /**
     * 构造函数
     * @param value 值
     * @param remark 描述
     */
     HttpStatusCode(int value,String remark){

        this.value = value;
        this.remark = remark;
    }

    /**
     * 获取值
     *
     * @return
     */
    @Override
    public Integer getValue() {
        return value;
    }

    /**
     * 备注
     *
     * @return
     */
    @Override
    public String getRemark() {
        return remark;
    }
}
