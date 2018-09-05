package com.gold.moni.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "修改用户信息")
public class UpdateUserInfoParam {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id",required = true)
    private int id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    /**
     * 客户Id
     */
    @ApiModelProperty(value = "客户Id")
    private Integer login;
}
