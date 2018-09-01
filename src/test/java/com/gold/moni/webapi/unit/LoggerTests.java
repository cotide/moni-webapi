package com.gold.moni.webapi.unit;

import com.gold.moni.webapi.WebapiApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTests extends WebapiApplicationTests {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void Log4jTest(){
        log.info("this is info log");
        log.debug("this is debug log");
        log.warn("this is warn log");
        log.error("this is error log");
    }

    @Test
    public void LogHelperTest()
    {
        logHelper.info("info title","this is info log");
        logHelper.warn("warn title","this is warn log");
        logHelper.error("error title","this is error log");

    }
}
