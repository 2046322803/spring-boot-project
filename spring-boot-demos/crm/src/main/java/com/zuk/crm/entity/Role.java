package com.zuk.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuk.crm.util.GenPrimaryKeyUtils;

@Entity
@Table(name = "system_role")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 32)
	private String id = GenPrimaryKeyUtils.getUUID();

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	public Role() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
