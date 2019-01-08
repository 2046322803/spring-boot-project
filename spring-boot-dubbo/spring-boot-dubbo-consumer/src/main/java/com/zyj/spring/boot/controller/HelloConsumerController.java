package com.zyj.spring.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zyj.spring.boot.IHelloService;

@RestController
public class HelloConsumerController {

	@Reference(version = "${dubbo.iconsumer.version}")
	private IHelloService iHelloService;

	@RequestMapping("/sayHello")
	public String sayHello(@RequestParam String name) {
		return iHelloService.sayHello(name);
	}

}
