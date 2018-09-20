package com.gold.moni.webapi.config;

import com.alibaba.fastjson.JSON;
import com.gold.moni.helper.common.api.HttpStatusCode;
import com.gold.moni.helper.common.api.WebResult;
import com.google.common.collect.Ordering;
import io.swagger.models.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;


@Component
public class SwaggerConfig {

    /**
     * 是否启用SwaggerUI
     */
    @Value("${swagger.enable}")
    private boolean isEnable;


    @Value("${jwt.header}")
    private String tokenHeader;


    @Value("${jwt.prefix}")
    private String tokenPrefix;


    /**
     * 初始化Swagger
     * @param tag 标签: v1.0,v1.1,v1.2...
     * @param version 版本: 1.0.0,1.1.0,1.2.0
     * @return
     */
    public Docket build(String tag, String version){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(String.format("api-%s (v%s)",tag ,version))
                // api描述
                .apiInfo(new ApiInfoBuilder()
                        .title("模拟盘 api")
                        .description("内部接口，不外用")
                        .version(version)
                        .build())
                .select()
                // 指定扫描ApiController 包位置
                .apis(RequestHandlerSelectors.basePackage("com.gold.moni.webapi.controller"))
                .paths(regex(String.format("/api/%s.*/*",tag)))
                .build()
                .directModelSubstitute(LocalDate.class,java.sql.Date.class)
                .directModelSubstitute(OffsetDateTime.class,java.util.Date.class)
                .useDefaultResponseMessages(false)
                // 自定义可选参数
                //.globalOperationParameters(operationParameters())
                // 自定义全局相应格式
                .globalResponseMessage(RequestMethod.GET,responseMessage())
                .globalResponseMessage(RequestMethod.POST,responseMessage())
                .globalResponseMessage(RequestMethod.PUT,responseMessage())
                .globalResponseMessage(RequestMethod.DELETE,responseMessage())
                .enable(isEnable);
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
        globalParameters.add(new ParameterBuilder()
                .name(tokenHeader.toLowerCase())
                .description("访问Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue(tokenPrefix)
                .build());
        return globalParameters;
    }

    // endregion
}
