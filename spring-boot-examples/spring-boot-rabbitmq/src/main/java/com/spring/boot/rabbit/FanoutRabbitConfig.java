package com.spring.boot.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类：分发模式(Fanout Exchange)
 * 
 * 在代码中我们配置了三个队列名、一个fanout交换机，并且将这三个队列绑定到了fanout交换器上。只要我们往这个交换机生产新的消息，那么这三个队列都会收到。
 *
 */
@Configuration
public class FanoutRabbitConfig {

	@Bean
	public Queue AMessage() {
		return new Queue("fanout.A");
	}

	@Bean
	public Queue BMessage() {
		return new Queue("fanout.B");
	}

	@Bean
	public Queue CMessage() {
		return new Queue("fanout.C");
	}

	/**
	 * 定义个fanout交换器
	 * 
	 * @return
	 */
	@Bean
	FanoutExchange fanoutExchange() {
		// 定义一个名为fanoutExchange的fanout交换器
		return new FanoutExchange("fanoutExchange");
	}

	/**
	 * 将定义的fanoutA队列与fanoutExchange交换机绑定
	 * 
	 * @return
	 */
	@Bean
	Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}

	/**
	 * 将定义的fanoutB队列与fanoutExchange交换机绑定
	 * 
	 * @return
	 */
	@Bean
	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}

	/**
	 * 将定义的fanoutC队列与fanoutExchange交换机绑定
	 * 
	 * @return
	 */
	@Bean
	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}

}
