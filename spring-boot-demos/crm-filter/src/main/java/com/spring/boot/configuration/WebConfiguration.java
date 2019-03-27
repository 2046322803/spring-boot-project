package com.spring.boot.configuration;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.filter.SessionFilter;

@Configuration
public class WebConfiguration {

	public static final String LOGIN_KEY = "LOGIN_SESSION_KEY";
	public static final String LOGIN_USER = "LOGIN_SESSION_USER";

	@Bean(name = "sessionFilter")
	public Filter sessionFilter() {
		return new SessionFilter();
	}

	@Bean
	public FilterRegistrationBean<?> filterRegistration() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
		registration.setFilter(sessionFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("sessionFilter");
		registration.setOrder(1);
		return registration;
	}

}