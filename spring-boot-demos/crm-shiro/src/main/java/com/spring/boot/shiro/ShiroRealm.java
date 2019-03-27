package com.spring.boot.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.spring.boot.dao.MenuDao;
import com.spring.boot.dao.OperateDao;
import com.spring.boot.dao.RoleDao;
import com.spring.boot.dao.RoleMenuDao;
import com.spring.boot.dao.RoleOperateDao;
import com.spring.boot.dao.UserDao;
import com.spring.boot.entity.Menu;
import com.spring.boot.entity.Operate;
import com.spring.boot.entity.Role;
import com.spring.boot.entity.User;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private OperateDao operateDao;

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Autowired
	private RoleOperateDao roleOperateDao;

	/**
	 * 认证实现
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (null == token.getPrincipal()) {
			return null;
		}

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		// 通过dao查找当前用户名对应的用户
		User user = userDao.findByName(username);
		if (null == user) {
			throw new UnknownAccountException("This username: " + username + " is not exist");
		}

		Optional<Role> role = roleDao.findById(user.getRoleId());
		String roleName = role.get().getCode();

		List<Menu> pmenuList = new ArrayList<Menu>();
		List<Menu> cmenuList = new ArrayList<Menu>();
		Set<Object> operateSet = new HashSet<Object>();
		if ("SUPER".equals(roleName)) {
			List<Menu> menuList = menuDao.findAll();
			for (Menu menu : menuList) {
				if (menu.getParentId() == null || "".equals(menu.getParentId())) {
					pmenuList.add(menu);
				} else {
					cmenuList.add(menu);
				}
			}
			List<Operate> operateList = operateDao.findAll();
			for (Operate operate : operateList) {
				if (!StringUtils.isEmpty(operate.getHref())) {
					operateSet.add(StringUtils.replace(operate.getHref(), "/**", ""));
				}
			}
		} else {
			List<Menu> menuList = roleMenuDao.queryMenuByRoleId(role.get().getId());
			for (Menu menu : menuList) {
				if (StringUtils.isEmpty(menu.getParentId())) {
					pmenuList.add(menu);
				} else {
					cmenuList.add(menu);
				}
			}
			List<Operate> operateList = roleOperateDao.queryOperateByRoleId(role.get().getId());
			for (Operate operate : operateList) {
				if (!StringUtils.isEmpty(operate.getHref())) {
					operateSet.add(StringUtils.replace(operate.getHref(), "/**", ""));
				}
			}
		}

		// 认证缓存信息
		return new SimpleAuthenticationInfo(new ShiroUser(user, roleName, pmenuList, cmenuList, operateSet),
				user.getPassword(), getName());
	}

	/**
	 * 授权实现
	 * 
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		if (null != shiroUser.getRoleName()) {
			info.addRole(shiroUser.getRoleName());
		}
		if (shiroUser.getOperateSet() != null) {
			for (Object href : shiroUser.getOperateSet()) {
				info.addStringPermission(href.toString());
			}
		}
		return info;
	}

}
