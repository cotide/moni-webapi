package com.gold.moni.webapi.controller.v1;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.gold.moni.webapi.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/v1/home")
@Api(description = "测试模块",produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController extends BaseApiController {

    @ApiOperation(value = "输出hello信息",notes = "此<b>hello</b>接口描述内容")
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello World";
    }

}
