package com.gold.moni.webapi.filter.jwt.common;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Getter
public class JwtUser extends BaseUserDetails {

    /**
     * 主键Id
     */
    @Getter
    private final Integer id;

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
            Integer id,
            String username,
            Date lastLoginDate) {
        this.id = id;
        this.username = username;
        this.lastLoginDate = lastLoginDate;
    }

}
