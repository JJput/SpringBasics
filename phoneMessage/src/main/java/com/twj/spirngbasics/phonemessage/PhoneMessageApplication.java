package com.twj.spirngbasics.phonemessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = "com.twj.spirngbasics")
@Slf4j
public class PhoneMessageApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PhoneMessageApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("短信服务启动成功!");
        log.info("发送测试接口:http://localhost:{}/{}/test/sendmsg", env.getProperty("server.port"),env.getProperty("spring.application.name"));
    }

}
