package com.spring.boot.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.spring.boot.dao.OperateDao;
import com.spring.boot.entity.Operate;
import com.spring.boot.shiro.ShiroRealm;

@Configuration
public class ShiroConfiguration {

	@Autowired
	private OperateDao operateDao;

	@Bean
	public Realm realm() {
		return new ShiroRealm();
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

		chainDefinition.addPathDefinition("/js/**", "anon");
		chainDefinition.addPathDefinition("/css/**", "anon");
		chainDefinition.addPathDefinition("/images/**", "anon");
		chainDefinition.addPathDefinition("/fonts/**", "anon");
		chainDefinition.addPathDefinition("/**/favicon.ico", "anon");

		Map<String, String> pathDefinitions = new HashMap<String, String>();
		List<Operate> operateList = operateDao.findAll();
		for (Operate operate : operateList) {
			if (!StringUtils.isEmpty(operate.getHref())) {
				pathDefinitions.put(operate.getHref(),
						"authc, perms[" + StringUtils.replace(operate.getHref(), "/**", "") + "]");
			}
		}
		chainDefinition.addPathDefinitions(pathDefinitions);
		chainDefinition.addPathDefinition("/**", "authc");

		// all paths are managed via annotations
		// chainDefinition.addPathDefinition("/**", "anon");

		// or allow basic authentication, but NOT require it.
		// chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");

		// 三种方式实现定义权限路径
		// 第一种:使用角色名定义
		// chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

		// 第二种:使用权限code定义
		// chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

		// 第三种:使用接口的自定义配置(此处配置之后需要在对应的接口使用@RequiresPermissions(""))
		// chainDefinition.addPathDefinition("/**", "authc");

		return chainDefinition;
	}

	@Bean
	protected CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

}
