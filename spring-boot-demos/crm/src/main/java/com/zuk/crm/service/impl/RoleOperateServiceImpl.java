package com.zuk.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.crm.dao.RoleOperateDao;
import com.zuk.crm.entity.Role;
import com.zuk.crm.entity.RoleOperate;
import com.zuk.crm.entity.RoleOperatePK;
import com.zuk.crm.service.RoleOperateService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleOperateServiceImpl implements RoleOperateService {

	@Autowired
	private RoleOperateDao roleOperateDao;

	@Override
	public List<RoleOperate> list(String roleId) {
		return roleOperateDao.queryByRoleId(roleId);
	}

	@Override
	public void save(Role role, String[] operateIdArray) {
		String roleId = role.getId();
		saveRoleOperate(operateIdArray, roleId);
	}

	@Override
	public void update(Role role, String[] operateIdArray) {
		String roleId = role.getId();
		roleOperateDao.deleteByRoleId(roleId);
		saveRoleOperate(operateIdArray, roleId);
	}

	private void saveRoleOperate(String[] operateIdArray, String roleId) {
		if (operateIdArray != null) {
			RoleOperatePK pk = null;
			RoleOperate roleOperate = null;
			List<RoleOperate> roleOperateList = new ArrayList<RoleOperate>(16);
			for (String operateId : operateIdArray) {
				roleOperate = new RoleOperate();
				pk = new RoleOperatePK();
				pk.setRoleId(roleId);
				pk.setOperateId(operateId);
				roleOperate.setPk(pk);

				roleOperateList.add(roleOperate);
			}
			roleOperateDao.saveAll(roleOperateList);
		}
	}

}
