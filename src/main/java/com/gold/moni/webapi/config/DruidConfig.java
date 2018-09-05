package com.gold.moni.webapi.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Druid 连接池
 */
@Configuration
public class DruidConfig {

    @Bean(name = "mainDataSource")
    public DataSource mainDataSource(Environment evn)
    {
        return getDataSource(evn);
    }

    // region Helper

    private DataSource getDataSource(Environment evn)
    {
        return getDataSource("main",evn);
    }

    private DataSource getDataSource(String dbName,Environment evn)
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(evn.getProperty(String.format("%s.datasource.url",dbName)));
        dataSource.setUsername(evn.getProperty(String.format("%s.datasource.username",dbName)));
        dataSource.setPassword(evn.getProperty(String.format("%s.datasource.password",dbName)));
        dataSource.setDriverClassName(evn.getProperty(String.format("%s.datasource.driver-class-name",dbName)));
        return dataSource;
    }

    // endregion
}
