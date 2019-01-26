package com.zuk.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.crm.dao.RoleDao;
import com.zuk.crm.entity.Role;
import com.zuk.crm.service.RoleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void init() {
		long count = roleDao.count();
		if (0L == count) {
			Role role = new Role();
			role.setCode("SUPER");
			role.setName("超级管理员");
			roleDao.save(role);
		}
	}

	@Override
	public void merge(Role role) {
		roleDao.save(role);
	}

	@Override
	public void delete(String id) {
		roleDao.deleteById(id);
	}

	@Override
	public Role get(String id) {
		return roleDao.getOne(id);
	}

	@Override
	public List<Role> list() {
		return roleDao.findAll();
	}

	@Override
	public Page<Role> listPage(Pageable pageable) {
		return roleDao.findAll(pageable);
	}

}
