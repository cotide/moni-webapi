package com.gold.moni.webapi.filter.jwt.attr;


import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface  PowerFilter {

    boolean vaildate() default true;
}
