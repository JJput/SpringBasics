package com.twj.spirngbasics.business;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //设置不拦截接口
    //!!!!!!!!!!不拦截接口尽量不要使用**作为规则!!!!!!!!!!
    private static final String[] EXCLUDE_PATH = {
            "/test/**",

            //Swagger-ui
            "/doc.html",
            "/swagger-resources/**",
            "/favicon.ico",
            "/webjars/**",
            "/error",

            //字典
            "/sysDict/**",
    };

    /**
     * 部分拦截接口，但无token的情况下也允许访问
     */
    public static final String[] NOT_TOKEN_RELEASE_PATH = {

    };


    public static final String TOKEN_FLAG = "token";

    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new InterceptorBusiness());
        for (String path : EXCLUDE_PATH) {
            interceptorRegistration.excludePathPatterns(path);
        }
        //设置拦截接口
        interceptorRegistration.addPathPatterns("/**");
    }
}
