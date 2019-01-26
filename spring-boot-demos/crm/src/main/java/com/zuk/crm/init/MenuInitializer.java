package com.zuk.crm.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zuk.crm.service.MenuService;

@Order(3)
@Component
public class MenuInitializer implements CommandLineRunner {

	@Autowired
	private MenuService menuService;

	@Override
	public void run(String... args) throws Exception {
		menuService.init();
	}

}
