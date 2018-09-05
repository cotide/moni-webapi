package com.gold.moni.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.security.util.Password;

import java.util.Date;

@Data
@ApiModel(description = "用户信息")
public class UserInfoDto  {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户Id")
    private int userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 客户号
     */
    @ApiModelProperty(value = "客户号")
    private int login;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", example = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;
}
