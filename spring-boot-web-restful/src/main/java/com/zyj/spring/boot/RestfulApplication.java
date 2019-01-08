
package com.zyj.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import com.zyj.spring.boot.model.Message;
import com.zyj.spring.boot.repository.InMemoryMessageRepository;
import com.zyj.spring.boot.repository.MessageRepository;


@SpringBootApplication
public class RestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}

}
