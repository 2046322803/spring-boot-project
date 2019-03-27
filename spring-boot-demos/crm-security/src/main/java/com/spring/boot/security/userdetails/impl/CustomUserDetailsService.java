package com.spring.boot.security.userdetails.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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

@Service
public class CustomUserDetailsService implements UserDetailsService {

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

	// 重写UserDetailsService接口里面的抽象方法
	// 根据用户名 返回一个UserDetails的实现类的实例
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 通过dao查找当前用户名对应的用户
		User user = userDao.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("This username: " + username + "is not exist");
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
				operateSet.add(operate.getHref());
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
				operateSet.add(operate.getHref());
			}
		}

		// 查到User后将其封装为UserDetails的实现类的实例供程序调用
		// 用该User和它对应的Role实体们构造UserDetails的实现类

		// 返回一个定制的UserDetails
		// AuthorityUtils.createAuthorityList(admin.getRole())就是将我们该用户所有的权限（角色）生成一个集合
		return new CustomUserDetails(user, roleName, pmenuList, cmenuList, operateSet);
	}

}
