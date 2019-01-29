package com.spring.boot.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类：通配符模式（Topic Exchange）
 */
@Configuration
public class TopicRabbitConfig {

	final static String message = "topic.message";
	final static String messages = "topic.messages";

	@Bean
	public Queue queueMessage() {
		return new Queue(TopicRabbitConfig.message);
	}

	@Bean
	public Queue queueMessages() {
		return new Queue(TopicRabbitConfig.messages);
	}

	/**
	 * 定义个topic交换器
	 * 
	 * @return
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("topicExchange");
	}

	/**
	 * 将定义的topic.message队列与topicExchange交换机绑定
	 * 
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	/**
	 * 将定义的topic.messages队列与topicExchange交换机绑定
	 * 
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}
	
}
