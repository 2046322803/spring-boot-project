package com.spring.boot.service;

import java.util.List;

import com.spring.boot.entity.Role;
import com.spring.boot.entity.RoleOperate;

public interface RoleOperateService {

	List<RoleOperate> list(String roleId);

	void save(Role role, String[] operateIdArray);

	void update(Role role, String[] operateIdArray);

}
