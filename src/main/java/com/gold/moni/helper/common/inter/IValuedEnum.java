package com.gold.moni.helper.common.inter;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import io.swagger.models.auth.In;

import java.lang.reflect.Type;

/**
 * 枚举声明
 */
public interface IValuedEnum extends JSONSerializable{


    /**
     * 获取值
     * @return
     */
     Integer getValue();


    /**
     * 备注
     * @return
     */
    String getRemark();


    @Override
    default void write(JSONSerializer jsonSerializer, Object o, Type type, int i) {
        jsonSerializer.write(getValue());
    }
}
