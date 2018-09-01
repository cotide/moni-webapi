package com.gold.moni.webapi;

import com.gold.moni.helper.logging.Log4jUtil;
import com.gold.moni.webapi.base.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebapiApplicationTests extends BaseTest {

   protected Log4jUtil logHelper =  Log4jUtil.getLogger(this.getClass());

    @Test
    public void contextLoads() {

    }

}
