package com.twj.spirngbasics.oss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = "com.twj.spirngbasics.oss")
@Slf4j
public class OSSApplication {
    
    public static void main(String[] args) {
//		SpringApplication.run(EurkaApplication.class, args);
        SpringApplication app = new SpringApplication(OSSApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("对象存储启动成功!");
        log.info("system地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
