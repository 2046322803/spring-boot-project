package com.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spring.boot.mapper")
public class MXApplication {

	public static void main(String[] args) {
		SpringApplication.run(MXApplication.class, args);
	}
}
