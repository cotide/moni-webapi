package com.gold.moni.helper.common.inter;

import com.alibaba.fastjson.serializer.JSONSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public  class JSONEnum implements  IValuedEnum {


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
    public JSONEnum(int value,String remark){

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
        return null;
    }

    /**
     * 获取描述
     *
     * @return
     */
    @Override
    public String getRemark() {
        return null;
    }

    /**
     * 格式化处理
     * @param jsonSerializer
     * @param o
     * @param type
     * @param i
     */
    @Override
    public
    void write(JSONSerializer jsonSerializer, Object o, Type type, int i) {

        jsonSerializer.write(value);
    }
}
