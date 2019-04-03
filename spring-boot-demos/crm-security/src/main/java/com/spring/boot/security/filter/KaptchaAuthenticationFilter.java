package com.spring.boot.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.google.code.kaptcha.Constants;

public class KaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private String servletPath;

	private String failureUrl;

	public KaptchaAuthenticationFilter(String servletPath, String failureUrl) {
		super(servletPath);
		this.servletPath = servletPath;
		this.failureUrl = failureUrl;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if ("POST".equalsIgnoreCase(req.getMethod()) && servletPath.equals(req.getServletPath())) {
			String expect = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (expect != null && !expect.equalsIgnoreCase(req.getParameter("kaptcha"))) {
				res.sendRedirect(failureUrl);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return null;
	}

}
