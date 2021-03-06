package com.spring.boot.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {

	@JmsListener(destination = "neo.topic")
	public void receiveTopic(String text) {
		System.out.println("Consumer2 topic msg : "+text);
	}
}