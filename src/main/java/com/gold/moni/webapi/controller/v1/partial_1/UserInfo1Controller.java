package com.gold.moni.webapi.controller.v1.partial_1;


import com.gold.moni.domain.dto.UserInfoDto;
import com.gold.moni.tasks.UserInfoTask;
import com.gold.moni.webapi.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.1/userInfo")
@Api(description = "用户模块")
public class UserInfo1Controller extends BaseApiController {

    @Autowired
    protected UserInfoTask userInfoTask;


    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public UserInfoDto getUser(@ApiParam(value = "用户Id",required = true)
                               @RequestParam("id") int id){
        return userInfoTask.get(id);
    }
}
