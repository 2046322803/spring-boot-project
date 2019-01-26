package com.zuk.crm.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zuk.crm.service.OperateService;

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
