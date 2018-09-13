package com.gold.moni.webapi.controller.v1;


import com.gold.moni.helper.exception.BusinessException;
import com.gold.moni.webapi.controller.base.AuthApiController;
import com.gold.moni.webapi.controller.base.BaseApiController;
import com.gold.moni.webapi.filter.jwt.attr.ActionPowerFilter;
import com.gold.moni.webapi.filter.jwt.attr.PowerFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/data")
@Api(description = "公用模块")
public class CommonController extends BaseApiController {


    @ApiOperation(value = "没有任何返回")
    @RequestMapping(value = "getNotReturn",method = RequestMethod.GET)
    public void  getNotReturn(){

    }


    @ApiOperation(value = "抛出异常")
    @RequestMapping(value = "getException",method = RequestMethod.GET)
    public void getException(){
        throw  new BusinessException("业务异常了... >_<");
    }


}
