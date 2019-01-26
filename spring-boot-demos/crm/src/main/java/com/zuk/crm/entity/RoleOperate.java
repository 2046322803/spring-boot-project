package com.zuk.crm.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "system_role_operate")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class RoleOperate implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleOperatePK pk;

	public RoleOperate() {
	}

	public RoleOperatePK getPk() {
		return pk;
	}

	public void setPk(RoleOperatePK pk) {
		this.pk = pk;
	}

}
