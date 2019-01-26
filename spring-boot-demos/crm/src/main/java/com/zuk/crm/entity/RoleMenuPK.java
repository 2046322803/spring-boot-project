package com.zuk.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleMenuPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "role_id", length = 32)
	private String roleId;

	@Column(name = "menu_id", length = 32)
	private String menuId;

	public RoleMenuPK() {
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
