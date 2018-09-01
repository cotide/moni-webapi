package com.gold.moni.webapi.controller.v1;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.gold.moni.domain.dto.UserInfoDto;
import com.gold.moni.helper.exception.BusinessException;
import com.gold.moni.webapi.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/common")
@Api(description = "公有模块",produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonController extends BaseApiController {



    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public UserInfoDto getUser(){
        return getUserData();
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "getUsers",method = RequestMethod.GET)
    public List<UserInfoDto> getUsers(){
        return getUsersData();
    }


    @ApiOperation(value = "没有任何返回")
    @RequestMapping(value = "getNotReturn",method = RequestMethod.GET)
    public void  getNotReturn(){

    }

    @ApiOperation(value = "抛出异常")
    @RequestMapping(value = "getException",method = RequestMethod.GET)
    public void getException(){
        throw  new BusinessException("业务异常了... >_<");
    }

    // region Helper

    private UserInfoDto getUserData(){

        UserInfoDto user = new UserInfoDto();
        user.setUserId(1);
        user.setUserName("测试用户1");
        user.setLogin(10086);
        user.setCreateTime(new Date());
        return user;
    }


    private List<UserInfoDto> getUsersData(){

        ArrayList<UserInfoDto> users = new ArrayList<>();
        UserInfoDto user1 = new UserInfoDto();
        user1.setUserId(1);
        user1.setUserName("测试用户1");
        user1.setLogin(10086);

        UserInfoDto user2 = new UserInfoDto();
        user1.setUserId(2);
        user1.setUserName("测试用户2");
        user1.setLogin(10087);

        UserInfoDto user3 = new UserInfoDto();
        user1.setUserId(2);
        user1.setUserName("测试用户3");
        user1.setLogin(10088);

        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

    // endregion

}
