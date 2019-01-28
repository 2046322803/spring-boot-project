package com.spring.boot.service.impl;

import org.springframework.stereotype.Service;

import com.spring.boot.service.HelloService;

@Service
public class HelloServieImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello service");
    }
}
