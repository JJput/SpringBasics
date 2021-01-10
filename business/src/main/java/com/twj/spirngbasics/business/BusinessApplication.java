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
        LOG.info("Swagger地址:http://localhost:{}/{}/swagger-ui.html", env.getProperty("server.port"),env.getProperty("spring.application.name"));
        LOG.info("Druid地址:http://localhost:{}/{}/druid", env.getProperty("server.port"), env.getProperty("spring.application.name"));
    }
}
