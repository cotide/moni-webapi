package com.gold.moni.webapi.controller.v2;


import com.gold.moni.webapi.domain.cmd.CreateUserInfoCmd;
import com.gold.moni.webapi.domain.cmd.UpdateUserInfoCmd;
import com.gold.moni.webapi.domain.param.CreateUserInfoParam;
import com.gold.moni.webapi.domain.param.UpdateUserInfoParam;
import com.gold.moni.webapi.tasks.UserInfoTask;
import com.gold.moni.webapi.controller.base.BaseApiController;
import com.gold.moni.webapi.filter.jwt.attr.ActionPowerFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v2.0/userInfo")
@Api(description = "用户模块")
public class UserInfo2Controller extends BaseApiController {


    @Autowired
    protected UserInfoTask userInfoTask;


    @ApiOperation(value = "创建用户")
    public void add(@Valid @RequestBody CreateUserInfoParam parm)
    {
        userInfoTask.add(new CreateUserInfoCmd(parm.getUserName()));
    }

    @ActionPowerFilter
    @ApiOperation(value = "修改用户")
    @ApiImplicitParams(value = {@ApiImplicitParam(
            name = "authorization",
            required = true,
            dataType = "string",
            paramType = "header",
            defaultValue = "Bearer ",
            value = "访问 Token")})
    @RequestMapping(value = "create",method = RequestMethod.PUT)
    public void update(@Valid @RequestBody UpdateUserInfoParam parm)
    {
        UpdateUserInfoCmd cmd = new UpdateUserInfoCmd(
                parm.getId(),
                parm.getUserName());
        cmd.setLogin(parm.getLogin());
        userInfoTask.update(cmd);
    }
}
