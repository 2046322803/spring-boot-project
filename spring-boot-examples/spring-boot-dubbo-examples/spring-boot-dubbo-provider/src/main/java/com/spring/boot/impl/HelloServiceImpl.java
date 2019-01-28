package com.spring.boot.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.spring.boot.IHelloService;

@Service(version = "${dubbo.iprovider.version}")
public class HelloServiceImpl implements IHelloService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
