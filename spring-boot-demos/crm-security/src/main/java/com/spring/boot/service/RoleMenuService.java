package com.spring.boot.service;

import java.util.List;

import com.spring.boot.entity.Role;
import com.spring.boot.entity.RoleMenu;

public interface RoleMenuService {

	List<RoleMenu> list(String roleId);

	void save(Role role, String[] menuIdArray);

	void update(Role role, String[] menuIdArray);

	void deleteByRoleId(String roleId);

}
