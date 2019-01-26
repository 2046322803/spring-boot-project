package com.zuk.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.crm.dao.RoleMenuDao;
import com.zuk.crm.entity.Role;
import com.zuk.crm.entity.RoleMenu;
import com.zuk.crm.entity.RoleMenuPK;
import com.zuk.crm.service.RoleMenuService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl implements RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public List<RoleMenu> list(String roleId) {
		return roleMenuDao.queryByRoleId(roleId);
	}

	@Override
	public void save(Role role, String[] menuIdArray) {
		String roleId = role.getId();
		saveRoleMenu(menuIdArray, roleId);
	}

	@Override
	public void update(Role role, String[] menuIdArray) {
		String roleId = role.getId();
		roleMenuDao.deleteByRoleId(roleId);
		saveRoleMenu(menuIdArray, roleId);
	}

	@Override
	public void deleteByRoleId(String roleId) {
		roleMenuDao.deleteByRoleId(roleId);
	}

	private void saveRoleMenu(String[] menuIdArray, String roleId) {
		if (menuIdArray != null) {
			RoleMenuPK pk = null;
			RoleMenu roleMenu = null;
			List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>(16);
			for (String menuId : menuIdArray) {
				roleMenu = new RoleMenu();
				pk = new RoleMenuPK();
				pk.setRoleId(roleId);
				pk.setMenuId(menuId);
				roleMenu.setPk(pk);

				roleMenuList.add(roleMenu);
			}
			roleMenuDao.saveAll(roleMenuList);
		}
	}
}
