package com.zuk.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleOperatePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "role_id", length = 32)
	private String roleId;

	@Column(name = "operate_id", length = 32)
	private String operateId;

	public RoleOperatePK() {
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

}
