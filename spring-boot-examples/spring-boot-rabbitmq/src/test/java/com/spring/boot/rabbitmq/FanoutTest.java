package com.spring.boot.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boot.rabbit.fanout.FanoutSender;
import com.spring.boot.rabbit.topic.TopicSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {

	@Autowired
	private FanoutSender sender;

	@Test
	public void fanoutSender() throws Exception {
		sender.send();
		Thread.sleep(1000l);
	}


}