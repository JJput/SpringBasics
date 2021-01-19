package com.twj.spirngbasics.business;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.twj.spirngbasics")
@EnableEurekaClient
@EnableScheduling   //开启定时计划
@MapperScan("com.twj.spirngbasics")
@ServletComponentScan // 注意要加上@ServletComponentScan注解，否则Servlet无法生效
@Slf4j
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功!");
        log.info("API接口文档:http://localhost:{}/{}/doc.html 使用Swagger2+swagger-bootstarp-ui开源地址:https://gitee.com/xiaoym/swagger-bootstrap-ui-demo", env.getProperty("server.port"),env.getProperty("spring.application.name"));
        log.info("数据库资源监视:http://localhost:{}/{}/druid 使用Druid", env.getProperty("server.port"), env.getProperty("spring.application.name"));
    }
}
