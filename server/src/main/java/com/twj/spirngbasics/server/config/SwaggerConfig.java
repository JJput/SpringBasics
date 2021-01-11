package com.twj.spirngbasics.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String HOST = "localhost:9001";

    @Bean
    public Docket api() {


        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("123")
                .required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .host(HOST)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))// 错误路径不监控
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalOperationParameters(pars);
//                .globalResponseMessage(RequestMethod.GET, list);

    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API文档",
                "HTTP状态401，未登录\n" +
                        "HTTP状态403，无权限",
                "V1.0",
                "",
                new Contact("", "", ""),
                "Apache", "http://www.apache.org/", Collections.emptyList());

    }
}