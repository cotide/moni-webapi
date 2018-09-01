package com.gold.moni.webapi.unit;

import com.gold.moni.webapi.controller.base.BaseApiController;
import com.gold.moni.webapi.controller.v1.HomeController;
import org.junit.Test;

public class ClassTest  {


    @Test
    public  void  isSub(){

        boolean result = BaseApiController.class.isAssignableFrom(HomeController.class);
        System.out.println(result);
    }
}
