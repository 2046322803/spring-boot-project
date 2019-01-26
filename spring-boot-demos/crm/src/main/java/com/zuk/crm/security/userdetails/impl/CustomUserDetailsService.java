package com.zuk.crm.security.userdetails.impl;

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

import com.zuk.crm.dao.MenuDao;
import com.zuk.crm.dao.OperateDao;
import com.zuk.crm.dao.RoleDao;
import com.zuk.crm.dao.RoleMenuDao;
import com.zuk.crm.dao.RoleOperateDao;
import com.zuk.crm.dao.UserDao;
import com.zuk.crm.entity.Menu;
import com.zuk.crm.entity.Operate;
import com.zuk.crm.entity.Role;
import com.zuk.crm.entity.User;

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
