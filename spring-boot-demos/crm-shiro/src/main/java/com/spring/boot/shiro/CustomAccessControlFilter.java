package com.spring.boot.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

public class CustomAccessControlFilter extends AccessControlFilter {

	/**
	 * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
	 * 
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		System.out.println(SecurityUtils.getSubject().isAuthenticated());
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			return false;
		}
		
		if (!ShiroUtils.checkAll()) {
			System.out.println(3);
			return false;
		}
		System.out.println(1);
		return true;
	}

	/**
	 * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println(2);
		
		return true;
	}

}
