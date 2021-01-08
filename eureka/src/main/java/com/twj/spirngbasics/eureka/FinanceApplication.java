package com.twj.spirngbasics.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaServer
public class FinanceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(FinanceApplication.class);

	public static void main(String[] args) {
//		SpringApplication.run(EurkaApplication.class, args);
		SpringApplication app = new SpringApplication(FinanceApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功!");
		LOG.info("服务器地址:\thttp://127.0.0.1:{}",env.getProperty("server.port"));
	}

}
