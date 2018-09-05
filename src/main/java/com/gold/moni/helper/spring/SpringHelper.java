package com.gold.moni.helper.spring;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;


    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static  Object getBean(String name){
        return  getApplicationContext().getBean(name);
    }

    public static  <T>  T getBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name,clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringHelper.applicationContext = applicationContext;
    }
}
