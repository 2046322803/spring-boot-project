package com.zuk.crm.service;

import java.util.List;

import com.zuk.crm.entity.Role;
import com.zuk.crm.entity.RoleOperate;

public interface RoleOperateService {

	List<RoleOperate> list(String roleId);

	void save(Role role, String[] operateIdArray);

	void update(Role role, String[] operateIdArray);

}
