package com.gold.moni.webapi.handler.advice;

import com.gold.moni.webapi.helper.common.api.HttpStatusCode;
import com.gold.moni.webapi.config.attr.Download;
import com.gold.moni.webapi.config.attr.IgnoreRequestFilter;
import com.gold.moni.webapi.controller.base.BaseApiController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author cotide
 */
@ControllerAdvice
public class WebApiResultAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        Class<?> subClass =methodParameter.getExecutable().getDeclaringClass();
        if(BaseApiController.class.isAssignableFrom(subClass))
        {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {


        if(methodParameter.getMethod().isAnnotationPresent(IgnoreRequestFilter.class)
                || methodParameter.getMethod().isAnnotationPresent(Download.class))
        {
            return o;
        }

        if(o == null)
        {
            return  new com.gold.moni.webapi.helper.common.api.WebResult("", HttpStatusCode.OK);
        }
        return new com.gold.moni.webapi.helper.common.api.extend.WebResult(o,HttpStatusCode.OK);
    }
}
