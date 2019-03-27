package com.spring.boot.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.boot.service.UserService;

@Order(2)
@Component
public class UserInitializer implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		userService.init();
	}
}
