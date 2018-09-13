package com.gold.moni.webapi.filter.jwt.data;

import lombok.Getter;

import java.util.Date;

public class JwtUser extends BaseUserDetails {

    /**
     * 主键Id
     */
    @Getter
    private final int id;

    /**
     * 用户名
     */
    @Getter
    private final String username;

    /**
     * 最后登录时间
     */
    @Getter
    private final Date lastLoginDate;

    public JwtUser(
            int id,
            String username,
            Date lastLoginDate) {
        this.id = id;
        this.username = username;
        this.lastLoginDate = lastLoginDate;
    }

}
