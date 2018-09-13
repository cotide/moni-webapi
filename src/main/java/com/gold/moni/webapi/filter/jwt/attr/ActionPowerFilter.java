package com.gold.moni.webapi.filter.jwt.attr;


import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionPowerFilter {

    boolean vaildate() default true;
}
