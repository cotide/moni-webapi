package com.gold.moni.webapi.controller.v1;

import com.gold.moni.domain.dto.UserInfoDto;
import com.gold.moni.domain.entity.UserInfo;
import com.gold.moni.domain.param.CreateUserInfoParam;
import com.gold.moni.helper.exception.BusinessException;
import com.gold.moni.tasks.UserInfoTask;
import com.gold.moni.webapi.controller.base.AuthApiController;
import com.gold.moni.webapi.controller.base.BaseApiController;
import com.gold.moni.webapi.filter.jwt.attr.ActionPowerFilter;
import com.gold.moni.webapi.vm.UserInfoVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1.0/userInfo")
@Api(description = "用户模块")
public class UserInfoController extends AuthApiController {

    @Autowired
    protected UserInfoTask userInfoTask;

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public UserInfoDto getUser(@ApiParam(value = "用户Id",required = true)
                               @RequestParam("id") int id){
       return userInfoTask.get(id);
    }

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "getUsers",method = RequestMethod.GET)
    public List<UserInfoDto> getUsers(){
        return userInfoTask.getList();
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "getCurrentUser",method = RequestMethod.GET)
    public UserInfoDto getCurrentUser(){
        return userInfoTask.get(currentUser().getId());
    }

}
