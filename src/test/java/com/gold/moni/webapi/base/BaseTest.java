package com.gold.moni.webapi.base;

import com.gold.moni.helper.spring.SpringHelper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;


@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {


    @Autowired
    private ApplicationContext context;


    @Before
    public void setUpAccplication() {
        new SpringHelper().setApplicationContext(context);
    }

    // region Helper

    protected DataSource getDataSource(){

        DataSource dbSource =  SpringHelper.getBean("mainDataSource", DataSource.class);
        assert (dbSource!=null):"DataSource is null";
        return dbSource;

    }

    // endregion
}
