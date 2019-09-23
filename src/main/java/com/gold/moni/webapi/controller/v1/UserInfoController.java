package com.gold.moni.webapi.controller.v1;

import com.gold.moni.webapi.domain.dto.UserInfoDto;
import com.gold.moni.webapi.tasks.UserInfoTask;
import com.gold.moni.webapi.controller.base.AuthApiController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1.0/userInfo")
@Api(description = "用户模块")
public class UserInfoController extends AuthApiController {

    @Autowired
    protected UserInfoTask userInfoTask;

    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParams(value = {@ApiImplicitParam(
            name = "authorization",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer ",
            value = "访问 Token")})
    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public UserInfoDto getUser(@ApiParam(value = "用户Id",required = true)
                               @RequestParam("id") int id){
       return userInfoTask.get(id);
    }


    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams(value = {@ApiImplicitParam(
            name = "authorization",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer ",
            value = "访问 Token")})
    @RequestMapping(value = "getUsers",method = RequestMethod.GET)
    public List<UserInfoDto> getUsers(){
        return userInfoTask.getList();
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @ApiImplicitParams(value = {@ApiImplicitParam(
            name = "authorization",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer ",
            value = "访问 Token")})
    @RequestMapping(value = "getCurrentUser",method = RequestMethod.GET)
    public UserInfoDto getCurrentUser(){
        return userInfoTask.get(currentUser().getId());
    }

}
