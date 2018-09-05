package com.gold.moni.webapi.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gold.moni.helper.common.api.HttpStatusCode;
import com.gold.moni.helper.common.api.WebResult;
import com.gold.moni.webapi.handler.ApplicationExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 *  swagger 配置
 */
@Configuration
@EnableSwagger2
@Profile({ "dev", "local" })
public class WebApiConfig  extends WebMvcConfigurationSupport
{
    @Autowired
    protected SwaggerConfig swagger;

    // region 版本定义

    @Bean
    public Docket swaggerApi_v1(){
        return swagger.build("v1.0","1.0.0");
    }

    @Bean
    public Docket swaggerApi_v1_1(){
        return swagger.build("v1.1","1.1.0");
    }

    @Bean
    public Docket swaggerApi_v1_2(){
        return swagger.build("v1.2","1.2.0");
    }

    @Bean
    public Docket swaggerApi_v2(){
        return swagger.build("v2.0","2.0.0");
    }

    @Bean
    public Docket swaggerApi_v2_1(){
        return swagger.build("v2.1","2.1.0");
    }

    // endregion

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 配置fastJson 用于替代jackson
     */
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {

        super.configureMessageConverters(converters);
        // 1.定义一个convert 转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2 添加fastjson 的配置信息 比如 是否要格式化 返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        // 格式化日期
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 解决乱码的问题
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        converters.add(fastConverter);
    }

    /**
     * 自定义全局异常
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(
            List<HandlerExceptionResolver> exceptionResolvers) {
        // 注入自定义异常处理
        exceptionResolvers.add(new ApplicationExceptionResolver());
        super.configureHandlerExceptionResolvers(exceptionResolvers);
    }


    // region Helper

}
