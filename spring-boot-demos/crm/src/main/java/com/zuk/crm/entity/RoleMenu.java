package com.zuk.crm.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "system_role_menu")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleMenuPK pk;

	public RoleMenu() {
	}

	public RoleMenuPK getPk() {
		return pk;
	}

	public void setPk(RoleMenuPK pk) {
		this.pk = pk;
	}

}
