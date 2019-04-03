package com.spring.boot.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.spring.boot.security.filter.KaptchaAuthenticationFilter;

/**
 * 继承WebSecurityConfigurerAdapter适配器且重写configure函数来实现访问的控制（那些访问/资源需要哪些权限）和登录的验证（数据库验证/内存验证）
 * 
 * @author zouyj
 *
 */
@Configuration // 这个就是java形式的bean spring3.0以后 允许以 @Configuration注解来代替XML形式的bean
@EnableWebSecurity // 开启 spring security配置验证
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfigurer.class);

	// 注入配置的钥匙
	@Value("${system.user.password.secret}")
	private String secret;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private FilterInvocationSecurityMetadataSource newSource;

	@Autowired
	private AccessDecisionManager accessDecisionManager;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private RequestMatcher logoutRequestMatcher;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	/**
	 * 在这里配置哪些页面不需要认证
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/fonts/**", "/**/favicon.ico",
				"/getKaptchaImage");
	}

	/**
	 * 定义认证用户信息获取来源，密码校验规则等
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1.inMemoryAuthentication 从内存中获取
		// auth.inMemoryAuthentication().passwordEncoder(new
		// BCryptPasswordEncoder()).withUser("user1").password(new
		// BCryptPasswordEncoder().encode("123123")).roles("USER");

		// 2.jdbcAuthentication从数据库中获取，但是默认是以security提供的表结构
		// usersByUsernameQuery 指定查询用户SQL
		// authoritiesByUsernameQuery 指定查询权限SQL
		// auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);

		// 3.注入userDetailsService，需要实现userDetailsService接口
		// 密码编码器
		// PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(secret);
		auth.userDetailsService(userDetailsService) // 注册自己定制的UserDetailsService
				.passwordEncoder(passwordEncoder); // 配置密码加密器
	}

	/**
	 * 定义安全策略，这是一个配置，相当于xml，Spring只会加载一次，所以以上的方法Spring是初始化一次的
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 在认证用户名之前认证验证码，如果验证码错误，将不执行用户名和密码的认证
		http.addFilterBefore(new KaptchaAuthenticationFilter("/login", "/validate"),
				UsernamePasswordAuthenticationFilter.class).authorizeRequests()// 限定只对签名成功的用户请求
				// .antMatchers("/static/**")
				// .permitAll()// 允许所有人访问
				// .anyRequest()// 限定所有请求
				// .authenticated()// 对所有签名成功的用户允许方法

				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
						o.setSecurityMetadataSource(newSource);
						o.setAccessDecisionManager(accessDecisionManager);
						return o;
					}
				}) //

				.and().requiresChannel().anyRequest().requiresSecure()// 使用HTTPS请求，需要生证书

				.and().formLogin()// 通过formLogin()配下的函数对登录form进行配置
				.loginPage("/login") // 指定登录页面
				.usernameParameter("name") // 指定页面中对应用户名的参数名称
				.passwordParameter("password") // 指定页面中对应密码的参数名称
				// .defaultSuccessUrl("/home") // 默认登录成功跳转地址
				// .failureUrl("/login") //默认登录失败跳转地址
				.permitAll()// 声明用户登录页面允许所有人访问
				.failureHandler(authenticationFailureHandler) // 登录失败处理器
				.successHandler(authenticationSuccessHandler) // 登录成功处理器

				.and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")// 启用remember me功能

				.and().logout()// 通过logout()配下的函数对注销进行配置
				.logoutRequestMatcher(logoutRequestMatcher)// 设置注销用的请求URL
				// .logoutSuccessUrl("")// 设置注销成功后的跳转URL
				.deleteCookies("JSESSIONID")// 消除Cookie
				.invalidateHttpSession(true)// 销毁Session
				.permitAll()// 声明用户退出页面允许所有人访问

				.and().sessionManagement()// 通过sessionManagement配下的函数对session配置
				// .invalidSessionUrl(invalidSessionUrl)
				// .maximumSessions(maximumSessions)//同一用户session上限设定 *比如同一个用户 多次登录
				// .expiredUrl(expiredUrl)

				// .and().httpBasic()// 启用HTTP基础认证，REST风格的网站就比较适合这样的验证

				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)

				.and().csrf().disable();// 关闭csrf，默认开启。框架默认开启了csrf，这样我们的ajax和表单提交等都需要提供一个token

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AccessDecisionManager accessDecisionManager() {
		// Security需要用到一个实现了AccessDecisionManager接口的类
		// 类功能：根据当前用户的信息，和目标url涉及到的权限，判断用户是否可以访问
		// 判断规则：用户只要匹配到目标url权限中的一个role就可以访问
		return new AccessDecisionManager() {

			@Override
			public boolean supports(Class<?> clazz) {
				return true;
			}

			@Override
			public boolean supports(ConfigAttribute attribute) {
				return true;
			}

			@Override
			public void decide(Authentication authentication, Object object,
					Collection<ConfigAttribute> configAttributes)
					throws AccessDeniedException, InsufficientAuthenticationException {
				// 迭代器遍历目标url的权限列表
				Iterator<ConfigAttribute> iterator = configAttributes.iterator();
				while (iterator.hasNext()) {
					ConfigAttribute ca = iterator.next();

					String needRole = ca.getAttribute();
					if ("ROLE_LOGIN".equals(needRole)) {
						if (authentication instanceof AnonymousAuthenticationToken) {
							logger.info("为登录");
							throw new BadCredentialsException("未登录");
						} else {
							return;
						}
					}

					// 遍历当前用户所具有的权限
					Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
					for (GrantedAuthority authority : authorities) {
						if (authority.getAuthority().equals(needRole)) {
							return;
						}
					}
				}

				// 执行到这里说明没有匹配到应有权限
				UserDetails user = (UserDetails) authentication.getPrincipal();
				logger.info("USER : " + user.getUsername() + " 权限不足");
				throw new AccessDeniedException("权限不足");
			}
		};
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
					response.sendRedirect("/wrong");
				} else if (exception instanceof DisabledException) {
					response.sendRedirect("/forbid");
				} else {
					response.sendRedirect("/failure");
				}
			}
		};
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				UserDetails user = (UserDetails) authentication.getPrincipal();
				logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
				response.sendRedirect("/home");
			}
		};
	}

	@Bean
	public RequestMatcher logoutRequestMatcher() {
		return new AntPathRequestMatcher("/logout");
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		// 自定义403响应内容
		return new AccessDeniedHandler() {

			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.sendRedirect("/warn");
			}
		};
	}

}