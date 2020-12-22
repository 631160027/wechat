package com.zhangbo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zhangbo")
@MapperScan(basePackages = "com.zhangbo.dao.*")
@Slf4j
public class WechatApplication {

	public static void main(String[] args) {
		log.info("SpringBoot Start...");
		SpringApplication.run(WechatApplication.class, args);
		log.info("SpringBoot Start Success");
	}

}
