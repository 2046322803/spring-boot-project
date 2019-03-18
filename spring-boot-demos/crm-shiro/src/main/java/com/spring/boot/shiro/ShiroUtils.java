package com.spring.boot.shiro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Shiro操作相关 工具类
 *
 * @author Larry.lv
 * @since 1.0.0 Create Date 2018-05-11 13:49 Copyright © 1985-2018 ZKTeco
 *        Inc.All right reserved.
 **/
public class ShiroUtils {

	/**
	 * 加盐参数
	 */
	public final static String hashAlgorithmName = "MD5";

	/**
	 * 循环次数
	 */
	public final static int hashIterations = 100;

	/**
	 * 获取当前 Subject
	 *
	 * @return Subject
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 认证通过或已记住的用户。与guset搭配使用。
	 *
	 * @return 用户：true，否则 false
	 */
	public static boolean isUser() {
		return getSubject() != null && getSubject().getPrincipal() != null;
	}

	/**
	 * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
	 *
	 * @return 访客：true，否则false
	 */
	public static boolean isGuest() {
		return !isUser();
	}

	/**
	 * 获取封装的 ShiroUser
	 *
	 * @return ShiroUser
	 */
	public static ShiroUser getUser() {
		if (isGuest()) {
			return null;
		} else {
			return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
		}
	}

	/**
	 * 验证当前用户是否拥有指定权限
	 *
	 * @param permission
	 *            权限名
	 * @return 拥有权限：true，否则false
	 */
	public static boolean hasPermission(String permission) {
		boolean te = getSubject().isPermitted(permission);
		return getSubject() != null && permission != null && permission.length() > 0
				&& getSubject().isPermitted(permission);
	}

	/**
	 * 校验当前用户是否有当前http请求的权限
	 * 
	 * @return
	 */
	public static boolean checkAll() {
		HttpServletRequest request = getRequest();
		ShiroUser user = getUser();
		if (null == user) {
			return false;
		}
		String requestURI = request.getRequestURI();
		if (hasPermission(requestURI)) {
			return true;
		}
		return false;
	}

	/**
	 * 未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户。。
	 *
	 * @return 没有通过身份验证：true，否则false
	 */
	public static boolean notAuthenticated() {
		return !isAuthenticated();
	}

	/**
	 * 已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
	 *
	 * @return 通过身份验证：true，否则false
	 */
	public static boolean isAuthenticated() {
		return getSubject() != null && getSubject().isAuthenticated();
	}

	/**
	 * 验证当前用户是否属于该角色？,使用时与lacksRole 搭配使用
	 *
	 * @param roleName
	 *            角色名
	 * @return 属于该角色：true，否则false
	 */
	public static boolean hasRole(String roleName) {
		return getSubject() != null && roleName != null && roleName.length() > 0 && getSubject().hasRole(roleName);
	}

	/**
	 * 与hasRole标签逻辑相反，当用户不属于该角色时验证通过。
	 *
	 * @param roleName
	 *            角色名
	 * @return 不属于该角色：true，否则false
	 */
	public static boolean lacksRole(String roleName) {
		return !hasRole(roleName);
	}

	/**
	 * 验证当前用户是否属于以下任意一个角色。
	 *
	 * @param roleList
	 *            角色列表
	 * @return 属于:true,否则false
	 */
	public static boolean hasAnyRoles(List<String> roleList) {
		boolean hasAnyRole = false;
		Subject subject = getSubject();
		if (subject != null && roleList != null && roleList.size() > 0) {
			for (String role : roleList) {
				if (subject.hasRole(role.trim())) {
					hasAnyRole = true;
					break;
				}
			}
		}
		return hasAnyRole;
	}

	/**
	 * 验证当前用户是否属于以下所有角色。
	 *
	 * @param roleList
	 *            角色列表
	 * @return 属于:true,否则false
	 */
	public static boolean hasAllRoles(List<String> roleList) {
		boolean hasAllRole = true;
		Subject subject = getSubject();
		if (subject != null && roleList != null && roleList.size() > 0) {
			for (String role : roleList) {
				if (!subject.hasRole(role.trim())) {
					hasAllRole = false;
					break;
				}
			}
		}
		return hasAllRole;
	}

	/**
	 * 从shiro获取session
	 *
	 */
	public static Session getSession() {
		return getSubject().getSession();
	}

	/**
	 * 获取shiro指定的sessionKey
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttr(String key) {
		Session session = getSession();
		return session != null ? (T) session.getAttribute(key) : null;
	}

	/**
	 * 移除shiro指定的sessionKey
	 */
	public static void removeSessionAttr(String key) {
		Session session = getSession();
		if (session != null)
			session.removeAttribute(key);
	}

	/**
	 * 获取 HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}

	/**
	 * 获取HttpServletRequest
	 * 
	 * @return request
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * 输出当前用户信息，通常为登录帐号信息。
	 *
	 * @return 当前用户信息
	 */
	public static String principal() {
		if (getSubject() != null) {
			Object principal = getSubject().getPrincipal();
			return principal.toString();
		}
		return "";
	}

	/**
	 * shiro密码加密工具类
	 *
	 * @param credentials
	 *            密码
	 * @return
	 */
	public static String md5(String credentials, String saltSource) {
		ByteSource salt = new Md5Hash(saltSource);
		return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
	}

}
