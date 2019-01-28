package com.spring.boot.scheduling;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduling {

	// 通过表达式来配置任务执行时间
	@Async
	@Scheduled(cron = "0/5 * * * * *")
	public void scheduled() {
		System.out.println("123");
	}

	// 定义一个按一定频率执行的定时任务
	@Async
	@Scheduled(fixedRate = 5000)
	public void scheduled1() {
		System.out.println("456");
	}

	// 定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间
	@Async
	@Scheduled(fixedDelay = 5000)
	public void scheduled2() {
		System.out.println("789");
	}

}
