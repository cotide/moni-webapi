package com.gold.moni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 程序入口
 */
@EnableCaching
@SpringBootApplication
public class WebapiApplication  extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(WebapiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebapiApplication.class);
    }

}
