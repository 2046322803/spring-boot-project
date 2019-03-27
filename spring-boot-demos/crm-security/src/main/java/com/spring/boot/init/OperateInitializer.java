package com.spring.boot.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spring.boot.service.OperateService;

@Order(4)
@Component
public class OperateInitializer implements CommandLineRunner {

	@Autowired
	private OperateService operateService;

	@Override
	public void run(String... args) throws Exception {
		operateService.init();
	}

}
