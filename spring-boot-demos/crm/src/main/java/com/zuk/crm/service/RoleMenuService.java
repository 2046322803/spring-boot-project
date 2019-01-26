package com.zuk.crm.service;

import java.util.List;

import com.zuk.crm.entity.Role;
import com.zuk.crm.entity.RoleMenu;

public interface RoleMenuService {

	List<RoleMenu> list(String roleId);

	void save(Role role, String[] menuIdArray);

	void update(Role role, String[] menuIdArray);

	void deleteByRoleId(String roleId);

}
