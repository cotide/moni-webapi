package com.gold.moni.webapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter();
//    }

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/",
            "/csrf",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/csrf",
            "/configuration/*",
            "/configuration/ui",
            "/configuration/security",
            // -- web api
            "/api/**",
            // -- other
            "favicon.ico"
    };


    /**
     * 请求规则
     * @param http
     * @throws Exception
     */
    @Override
    public void  configure(HttpSecurity http) throws Exception{

        // 禁止CSRF检查
        http.cors().and().csrf()
        .disable()
        .authorizeRequests()
        // 允许访问规则
        .antMatchers(AUTH_WHITELIST).permitAll()
        // 其他规则禁止访问
        .anyRequest().authenticated();
    }



}
