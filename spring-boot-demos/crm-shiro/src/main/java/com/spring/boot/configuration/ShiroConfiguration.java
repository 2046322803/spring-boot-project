package com.spring.boot.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.shiro.ShiroRealm;

@Configuration
public class ShiroConfiguration {

	@Value("${auth.allowed.resource.prefix}")
	private String allowedResourcePrefix;

	@Value("${auth.denied.resource.prefix}")
	private String deniedResourcePrefix;

	/**
	 * shiro 权限拦截
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/home");
		// 未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/warn");

		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// <!-- anon:所有url都都可以匿名访问；authc:所有url都必须认证通过才可以访问；-->
		for (String prefix : allowedResourcePrefix.split(";")) {
			filterChainDefinitionMap.put(prefix, "anon");
		}

		for (String prefix : deniedResourcePrefix.split(";")) {
			filterChainDefinitionMap.put(prefix, "authc");
		}
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}

	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}

}
