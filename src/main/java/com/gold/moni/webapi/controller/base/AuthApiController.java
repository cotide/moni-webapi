package com.gold.moni.webapi.controller.base;

import com.gold.moni.webapi.filter.jwt.attr.PowerFilter;
import com.gold.moni.webapi.filter.jwt.data.JwtUser;
import com.gold.moni.webapi.vm.UserInfoVM;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * 权限控制器
 */
@PowerFilter
public class AuthApiController extends BaseApiController {

    /**
     * 当前用户身份
     * @return
     */
    public UserInfoVM currentUser(){
       Object user = SecurityContextHolder
                       .getContext()
                       .getAuthentication()
                       .getPrincipal();
       return createUserInfoVM((JwtUser)user);
    }

    // region Helper

    private UserInfoVM createUserInfoVM(JwtUser jwtUser){
        return  new UserInfoVM(
                jwtUser.getId(),
                jwtUser.getUsername(),
                jwtUser.getLastLoginDate());
    }

    // endregion
}
