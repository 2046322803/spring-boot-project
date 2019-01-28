package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.spring.boot.controller")
public class DubboConsumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboConsumeApplication.class, args);
	}

}
