package com.gold.moni.domain.dto;


import lombok.Data;
import sun.security.util.Password;

import java.util.Date;

@Data
public class UserInfoDto  {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 客户号
     */
    private int login;

    /**
     * 创建时间
     */
    private Date createTime;
}
