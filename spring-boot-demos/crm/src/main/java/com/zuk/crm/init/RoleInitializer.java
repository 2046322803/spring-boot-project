package com.zuk.crm.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zuk.crm.service.RoleService;

@Order(1)
@Component
public class RoleInitializer implements CommandLineRunner {

	@Autowired
	private RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
		roleService.init();
	}
}
