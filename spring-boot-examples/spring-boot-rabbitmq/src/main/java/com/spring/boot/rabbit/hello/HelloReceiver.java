package com.spring.boot.rabbit.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq消费者 @RabbitListener(queues = "simpleMsg") 监听名simpleMsg的队列
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver  : " + hello);
	}

}
