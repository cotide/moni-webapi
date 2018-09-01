package com.gold.moni.webapi.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gold.moni.helper.common.api.HttpStatusCode;
import com.gold.moni.helper.common.api.WebResult;
import com.gold.moni.helper.exception.BusinessException;
import com.gold.moni.helper.exception.UnauthenticatedException;
import com.gold.moni.helper.fastjson.FastjsonFilterUtil;
import com.gold.moni.helper.logging.Log4jUtil;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationExceptionResolver implements HandlerExceptionResolver {


    /**
     * 日志服务
     */
    Log4jUtil logger = Log4jUtil.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        // 输出 异常信息
        logger.error("发生异常:", "[异常拦截]"+ ex.getMessage());
        // 将异常信息转json输出
        this.writeJsonByFilter(response, this.resolveExceptionCustom(ex), null,
                null);
        return new ModelAndView();
    }



    /**
     * 异常信息解析方法
     *
     * @param ex
     */
    private WebResult resolveExceptionCustom(Exception ex) {

        WebResult model =
                new WebResult(
                        ex.getMessage(),
                        HttpStatusCode.InternalServerError);

        if (ex instanceof UnauthenticatedException) {
            // 没有权限的异常
            new WebResult(ex.getMessage(), HttpStatusCode.Unauthorized);
        }
        else if(ex instanceof BusinessException)
        {
            // 业务异常
            new WebResult(ex.getMessage(),HttpStatusCode.BusinessError);
        }
        else {
            // 未知错误
            new WebResult(ex.getMessage(), HttpStatusCode.InternalServerError);
        }
        return model;
    }



    /**
     * 将对象转换成JSON字符串，并响应回前台
     */
    protected void writeJsonByFilter(HttpServletResponse response,
                                     Object object, String[] includesProperties,
                                     String[] excludesProperties) {
//        super.configureMessageConverters(converters);
//
//        converters.add(fastConverter);


        try {



//            // excludes优先于includes
//            FastjsonFilterUtil  filter = new FastjsonFilterUtil();
//            if (excludesProperties != null && excludesProperties.length > 0) {
//                filter.getExcludes().addAll(
//                        Arrays.<String> asList(excludesProperties));
//            }
//            if (includesProperties != null && includesProperties.length > 0) {
//                filter.getIncludes().addAll(
//                        Arrays.<String> asList(includesProperties));
//            }

//            // 1.定义一个convert 转换消息的对象
//            FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//            // 2 添加fastjson 的配置信息 比如 是否要格式化 返回的json数据
//            FastJsonConfig fastJsonConfig = new FastJsonConfig();
//            fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//            fastConverter.setFastJsonConfig(fastJsonConfig);
//
//            List<MediaType> fastMediaTypes = new ArrayList<>();
//            // 格式化日期
//            fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//            // 解决乱码的问题
//            fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//            fastConverter.setSupportedMediaTypes(fastMediaTypes);


            String json = JSON.toJSONString(object);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            logger.error("An error occurred when object was converted to JSON");
        }
    }


}
