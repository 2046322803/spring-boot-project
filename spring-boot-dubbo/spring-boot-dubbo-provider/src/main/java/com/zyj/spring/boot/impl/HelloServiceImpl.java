package com.zyj.spring.boot.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zyj.spring.boot.IHelloService;

@Service(version = "${dubbo.iprovider.version}")
public class HelloServiceImpl implements IHelloService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
