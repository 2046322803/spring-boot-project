package com.spring.boot.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

	public static final String LOGIN_KEY = "LOGIN_SESSION_KEY";
	public static final String LOGIN_USER = "LOGIN_SESSION_USER";

	@Bean(name = "sessionFilter")
	public Filter sessionFilter() {
		return new SessionFilter();
	}

	@Bean
	public FilterRegistrationBean<Filter> testFilterRegistration() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		registration.setFilter(sessionFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("sessionFilter");
		registration.setOrder(1);
		return registration;
	}
	
}