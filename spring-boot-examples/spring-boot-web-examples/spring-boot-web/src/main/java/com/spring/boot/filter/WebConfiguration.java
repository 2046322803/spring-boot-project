package com.spring.boot.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

	@Bean
	public FilterRegistrationBean<?> testFilterRegistration() {
		FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<MyFilter>();
		registration.setFilter(new MyFilter());
		registration.addUrlPatterns("/*");
		registration.setName("MyFilter");
		registration.setOrder(6);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<?> test2FilterRegistration() {
		FilterRegistrationBean<MyFilter2> registration = new FilterRegistrationBean<MyFilter2>();
		registration.setFilter(new MyFilter2());
		registration.addUrlPatterns("/*");
		registration.setName("MyFilter2");
		registration.setOrder(1);
		return registration;
	}
}