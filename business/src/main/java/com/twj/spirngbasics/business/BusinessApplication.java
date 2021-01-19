package com.twj.spirngbasics.business;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.twj.spirngbasics")
@EnableEurekaClient
@EnableScheduling
@MapperScan("com.twj.spirngbasics.server")
public class BusinessApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功!");
        LOG.info("API接口文档:http://localhost:{}/{}/doc.html 使用Swagger2+swagger-bootstarp-ui开源地址:https://gitee.com/xiaoym/swagger-bootstrap-ui-demo", env.getProperty("server.port"),env.getProperty("spring.application.name"));
        LOG.info("数据库资源监视:http://localhost:{}/{}/druid 使用Druid", env.getProperty("server.port"), env.getProperty("spring.application.name"));
    }
}
