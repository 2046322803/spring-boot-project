package com.zyj.spring.boot.configration;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//开启异步事件的支持
@EnableAsync
@Configuration
public class AsyncConfigration {

	// 定时任务的类或者方法上添加@Async，每一个任务都是在不同的线程中

	@Value("${core-pool-size}")
	private int corePoolSize;

	@Value("${max-pool-size}")
	private int maxPoolSize;

	@Value("${queue-capacity}")
	private int queueCapacity;

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.initialize();
		return executor;
	}

}
