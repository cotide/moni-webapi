package com.gold.moni.webapi.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gold.moni.webapi.domain.entity.enums.EnumGroup;
import com.gold.moni.webapi.domain.entity.enums.EnumUserStatus;
import com.gold.moni.webapi.domain.entity.enums.EnumVipLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "用户信息")
public class UserInfoDto  {

    @ApiModelProperty(value = "用户Id")
    private int userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "客户号")
    private int login;

    @ApiModelProperty(value = "用户状态")
    private EnumUserStatus status;

    @ApiModelProperty(value = "用户等级")
    private EnumVipLevel level;
 
    @ApiModelProperty(value = "用户分组")
    private EnumGroup group;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", example = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;
}
