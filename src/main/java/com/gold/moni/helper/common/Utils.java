package com.gold.moni.helper.common;

import java.util.Collection;

public class Utils {
    /**
     * 判断字符串是否不为Null或者空
     * @param value
     * @return
     */
    public static boolean isNotNullOrEmpty(String value) {
        return null != value && !value.isEmpty()  ;
    }

    /**
     * 判断字符串是否不为Null
     * @param value
     * @return
     */
    public static boolean isNotNull(Object value) {
        return null != value ;
    }


    /**
     * 判断字符串是否为Null
     * @param value
     * @return
     */
    public static boolean isNull(Object value) {
        return null == value ;
    }


    /**
     * 判断集合数据是否不为空
     * @param collection
     * @return
     */
    public static boolean isNotNullOrEmpty(Collection<?> collection) {
        return null != collection && !collection.isEmpty();
    }

    /**
     * 判断字符串是否为空
     * @param value
     * @return
     */
    public static boolean isNullOrEmpty(String value) {
        return null == value || value.isEmpty();
    }


}
