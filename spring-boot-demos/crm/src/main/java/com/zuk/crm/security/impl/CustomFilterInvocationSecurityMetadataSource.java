package com.zuk.crm.security.impl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.zuk.crm.dao.OperateDao;
import com.zuk.crm.dao.RoleOperateDao;
import com.zuk.crm.entity.Operate;
import com.zuk.crm.entity.Role;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LoggerFactory.getLogger(CustomFilterInvocationSecurityMetadataSource.class);

	@Autowired
	private OperateDao operateDao;

	@Autowired
	private RoleOperateDao roleOperateDao;

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	// 接收用户请求的地址，返回访问该地址需要的所有权限
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 得到用户的请求地址,控制台输出一下
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		logger.info("用户请求的地址是：" + requestUrl);

		// 如果登录页面就不需要权限
		if ("/login".equals(requestUrl) || "/wrong".equals(requestUrl) || "/forbid".equals(requestUrl)
				|| "/failure".equals(requestUrl)) {
			return null;
		}

		boolean hasOperate = true;
		String operateId = null;
		List<Operate> operateList = operateDao.findByParentIdIsNotNullOrderBySortAsc();
		for (Operate operate : operateList) {
			if (requestUrl.startsWith(operate.getHref())) {
				hasOperate = false;
				operateId = operate.getId();
				break;
			}
		}

		// 如果没有匹配的url则说明大家都可以访问
		if (hasOperate) {
			return SecurityConfig.createList("ROLE_LOGIN");
		}

		List<Role> roles = roleOperateDao.queryRoleByOperateId(operateId);
		int size = roles.size();
		String[] values = new String[size];
		for (int i = 0; i < size; i++) {
			values[i] = roles.get(i).getCode();
		}
		return SecurityConfig.createList(values);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
