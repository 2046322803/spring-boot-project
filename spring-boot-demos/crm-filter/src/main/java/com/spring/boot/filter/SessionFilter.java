package com.spring.boot.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.bean.AclBean;

public class SessionFilter implements Filter {
	protected Logger log = LoggerFactory.getLogger(SessionFilter.class);
	// 不登陆也可以访问的资源
	private static Set<String> GreenUrlSet = new HashSet<String>();

	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		String uri = request.getRequestURI();
		sresponse.setCharacterEncoding("UTF-8");// 设置响应编码格式
		sresponse.setContentType("text/html;charset=UTF-8");// 设置响应编码格式
		if (uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".gif")
				|| uri.endsWith(".png") || uri.endsWith(".ico") || uri.endsWith(".woff2") || uri.endsWith(".woff")
				|| uri.endsWith(".ttf")) {
			log.debug("security filter, pass, " + request.getRequestURI());
			filterChain.doFilter(srequest, sresponse);
			return;
		}

		System.out.println("request uri is : " + uri);
		// 不处理指定的action, jsp
		if (GreenUrlSet.contains(uri) || uri.contains("/verified/")) {
			log.debug("security filter, pass, " + request.getRequestURI());
			filterChain.doFilter(srequest, sresponse);
			return;
		}
		AclBean aclBean = (AclBean) request.getSession().getAttribute("aclBean");
		if (aclBean == null) {
			String html = "<script type=\"text/javascript\">window.location.href=\"/toLogin\"</script>";
			sresponse.getWriter().write(html);
		} else {
			boolean pass = false;
			for (Object operate : aclBean.getOperateSet()) {
				if (uri.startsWith(operate == null ? "" : operate.toString())) {
					pass = true;
					break;
				}
			}
			if (pass) {
				filterChain.doFilter(srequest, sresponse);
			} else {
				String html = "<script type=\"text/javascript\">window.location.href=\"/warn\"</script>";
				sresponse.getWriter().write(html);
			}
		}
	}

	public void destroy() {

	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		GreenUrlSet.add("/home");
		GreenUrlSet.add("/toLogin");
		GreenUrlSet.add("/login");
		GreenUrlSet.add("/logout");
		GreenUrlSet.add("/about");
		GreenUrlSet.add("/warn");
	}
}
