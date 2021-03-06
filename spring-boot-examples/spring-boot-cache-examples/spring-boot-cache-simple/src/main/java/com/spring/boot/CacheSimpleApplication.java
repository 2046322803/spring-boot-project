package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheSimpleApplication.class, args);
	}
}
