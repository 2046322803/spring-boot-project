package com.zuk.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuk.crm.util.GenPrimaryKeyUtils;

@Entity
@Table(name = "system_operate")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Operate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 32)
	private String id = GenPrimaryKeyUtils.getUUID();

	@Column(name = "name")
	private String name;

	@Column(name = "icon")
	private String icon;

	@Column(name = "href")
	private String href;

	@Column(name = "sort")
	private Integer sort;

	@Column(name = "parent_id")
	private String parentId;

	public Operate() {
	}

	public Operate(String name, String icon, String href, Integer sort, String parentId) {
		this.name = name;
		this.icon = icon;
		this.href = href;
		this.sort = sort;
		this.parentId = parentId;
	}
	
	public Operate(String id, String name, String icon, String href, Integer sort, String parentId) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.href = href;
		this.sort = sort;
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
