package com.gold.moni.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ApiModel(description = "修改用户信息")
public class UpdateUserInfoParam {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空")
    @Positive(message= "主键id格式不正确")
    @ApiModelProperty(value = "主键id",required = true)
    private int id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true)
    private String userName;

    /**
     * 客户Id
     */
    @ApiModelProperty(value = "客户Id")
    private Integer login;
}
