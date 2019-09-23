package com.gold.moni.webapi.handler;

import com.gold.moni.webapi.helper.exception.PowerException;
import com.gold.moni.webapi.tasks.UserInfoTask;
import com.gold.moni.webapi.tasks.identity.IdentityService;
import com.gold.moni.webapi.filter.jwt.JwtTokenUtil;
import com.gold.moni.webapi.filter.jwt.attr.ActionPowerFilter;
import com.gold.moni.webapi.filter.jwt.attr.PowerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;



@Component
public class WebApiPowerHandler extends HandlerInterceptorAdapter {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.prefix}")
    private String tokenPrefix;

    @Autowired
    private IdentityService identityService;


    /**
     * jwt辅助类
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){

            HandlerMethod  handlerMethod =  ((HandlerMethod)handler);
            Method method = handlerMethod.getMethod();
            if(method.getDeclaringClass().isAnnotationPresent(RestController.class))
            {
                if(method.getDeclaringClass().isAnnotationPresent(PowerFilter.class))
                {
                   // 进行权限验证
                    return vaildate(request);
                }

                ActionPowerFilter actionPowerFilter =
                        handlerMethod.getMethodAnnotation(
                                ActionPowerFilter.class);

                if(actionPowerFilter!=null)
                {
                    // 进行权限验证
                    return vaildate(request);
                }

            }



        }
        return true;
    }


    // region Helper

    /**
     * 权限验证
     * @param request
     * @return
     */
    private boolean vaildate(HttpServletRequest request){

        Enumeration<String> headers = request.getHeaderNames();
        // Token请求头
        String tokenHeaderStr = tokenHeader.toLowerCase();
        // 是否有请求Token
        boolean isHaveToken = false;
        while (headers.hasMoreElements())
        {
            String token = headers.nextElement();
            if(token!=null && token.toLowerCase().equals(tokenHeaderStr))
            {
                isHaveToken = true;
                break;
            }
        }
        if(!isHaveToken)
        {
            throw new PowerException(
                    String.format("%s header missing in request", tokenHeaderStr));
        }
        String token  =  request.getHeader(tokenHeaderStr);
        if(!token.startsWith(tokenPrefix))
        {
            throw new PowerException(
                    String.format("%s header error in request", tokenHeaderStr));
        }
        String authToken  = token.substring(tokenPrefix.length());
        String username = jwtTokenUtil.getUserNameFromToken(authToken);
        if(username==null||username.trim().equals(""))
        {
            throw new PowerException();
        }
        // 读取用户数据
        UserDetails userDetails =  identityService.loadUserByUsername(username);
        if(userDetails!=null)
        {
            if(jwtTokenUtil.validateToken(authToken,userDetails)){

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                return true;
            }
        }
        throw new PowerException();
    }
    // endregion

}
