package com.twj.spirngbasics.business;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //设置不拦截接口  !!!!!!!!!!不拦截接口尽量不要使用**作为规则!!!!!!!!!!
    private static final String[] EXCLUDE_PATH = {
            "/test/test",
            "/oss/createPushKey"
    };

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
