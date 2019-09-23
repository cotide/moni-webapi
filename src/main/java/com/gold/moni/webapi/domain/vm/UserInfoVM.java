package com.gold.moni.webapi.domain.vm;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserInfoVM {

    /**
     * 主键Id
     */
    private  int id;

    /**
     * 用户名
     */
    private  String username;

    /**
     * 最后登录时间
     */
    private  Date lastLoginDate;

    public UserInfoVM(int id,String username,Date lastLoginDate)
    {
        this.id = id;
        this.username = username;
        this.lastLoginDate = lastLoginDate;
    }
}
