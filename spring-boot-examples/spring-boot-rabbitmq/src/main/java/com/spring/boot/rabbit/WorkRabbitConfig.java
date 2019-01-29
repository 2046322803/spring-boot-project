package com.spring.boot.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类：工作队列模式(Work Queue)
 */
@Configuration
public class WorkRabbitConfig {

	/**
	 * 定义hello队列
	 * 
	 * @return
	 */
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}

	/**
	 * 定义neo队列
	 * 
	 * @return
	 */
	@Bean
	public Queue neoQueue() {
		return new Queue("neo");
	}

	/**
	 * 定义object队列
	 * 
	 * @return
	 */
	@Bean
	public Queue objectQueue() {
		return new Queue("object");
	}

}
