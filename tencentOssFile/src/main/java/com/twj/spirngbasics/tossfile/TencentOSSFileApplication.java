package com.twj.spirngbasics.tossfile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = "com.twj.spirngbasics.tossfile")
@EnableEurekaClient
@Slf4j
public class TencentOSSFileApplication {
    
    public static void main(String[] args) {
//		SpringApplication.run(EurkaApplication.class, args);
        SpringApplication app = new SpringApplication(TencentOSSFileApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("腾讯OSS对象存储管理器启动成功!");
        log.info("system地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
