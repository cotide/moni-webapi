package com.gold.moni.webapi.helper.logging;

import com.gold.moni.webapi.helper.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录
 */
public class Log4jUtil {


    protected  Logger logger;

    public Log4jUtil(Class<?> classModel){
        logger =  LoggerFactory.getLogger(classModel);
    }

    /**
     * 获取日志对象
     * @param classModel
     * @return
     */
    public static Log4jUtil getLogger(Class<?> classModel){
        return new Log4jUtil(classModel);
    }

    /**
     * 错误日志
     * @param title 标题
     * @param content 内容
     */
    public void error(String title,String content){

        logger.error(getFullMsg("Error",title,content));
    }

    /**
     * 错误日志
     * @param content 内容
     */
    public void error(String content){
        error(null,content);
    }


    /**
     * 信息日志
     * @param title 标题
     * @param content 内容
     */
    public void info(String title,String content){
        logger.info(getFullMsg("Info",title,content));
    }

    /**
     * 信息日志
     * @param content 内容
     */
    public void info(String content){
        info(null,content);
    }


    /**
     * 警告日志
     * @param title 标题
     * @param content 内容
     */
    public void warn(String title,String content){
        logger.warn(getFullMsg("Warn",title,content));
    }

    /**
     * 警告日志
     * @param content 内容
     */
    public void warn(String content){
        warn(null,content);
    }

    // region Helper

    private static String getFullMsg(
            String type,
            String title,
            String content)
    {
        StringBuilder msg = new StringBuilder();
        if(Utils.isNotNullOrEmpty(title))
        {
            msg.append(String.format("[%s][Title]=%s",type,title));
        }
        if(Utils.isNotNullOrEmpty(content))
        {
            msg.append(String.format("[ContextInfo]=%s",content));
        }
        return msg.toString();
    }
    // endregion


}
