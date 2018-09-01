package com.gold.moni.webapi.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gold.moni.helper.common.api.HttpStatusCode;
import com.gold.moni.helper.common.api.WebResult;
import com.gold.moni.webapi.handler.ApplicationExceptionResolver;
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
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *  swagger 配置
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2
@Profile({ "dev", "local" })
public class WebApiConfig  extends WebMvcConfigurationSupport
{

    @Bean
    public Docket creawteRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                 // 指定扫描ApiController 包位置
                .apis(RequestHandlerSelectors.basePackage("com.gold.moni.webapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(java.time.LocalDate.class,java.sql.Date.class)
                .directModelSubstitute(java.time.OffsetDateTime.class,java.util.Date.class)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                // 自定义可选参数
                .globalOperationParameters(operationParameters())
                // 自定义全局相应格式
                .globalResponseMessage(RequestMethod.GET,responseMessage())
                .globalResponseMessage(RequestMethod.POST,responseMessage())
                .globalResponseMessage(RequestMethod.PUT,responseMessage())
                .globalResponseMessage(RequestMethod.DELETE,responseMessage());

    }


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

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("模拟盘 api")
                .description("内部接口，不外用")
                .version("1.0.0")
                .build();
    }


    /**
     * 状态信息描述
     * @return
     */
    private ArrayList responseMessage()
    {
        ArrayList globalResponseMessage = new ArrayList();

        // 循环遍历接口状态信息
        for ( HttpStatusCode item :HttpStatusCode.values())
        {
            StringBuilder outStr = new StringBuilder();
            // 响应数据格式
            WebResult resultModel = new WebResult(item.getRemark(),item);
            outStr.append(String.format("[%s]",item.getRemark()));
            outStr.append(String.format("[JSON=]%s", JSON.toJSONString(resultModel)));
            globalResponseMessage.add(
                    new ResponseMessageBuilder()
                            .code(item.getValue())
                            .message(outStr.toString())
                            .build());
        }

        return globalResponseMessage;
    }


    /**
     * 可选参数描述
     * @return
     */
    private ArrayList operationParameters()
    {

        ArrayList globalParameters = new ArrayList();
//        globalParameters.add(new ParameterBuilder()
//                .name("header")
//                .description("Description of header")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(true)
//                .build());
        return globalParameters;

    }

    // endregion
}
