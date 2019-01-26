package com.zuk.crm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zuk.crm.entity.Menu;

public class AclBean implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String password;
	private String picture;
	private String roleName;
	private Set<Object> operateSet = new HashSet<Object>();
	private List<Menu> pmenuList = new ArrayList<Menu>();
	private List<Menu> cmenuList = new ArrayList<Menu>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Object> getOperateSet() {
		return operateSet;
	}

	public void setOperateSet(Set<Object> operateSet) {
		this.operateSet = operateSet;
	}

	public List<Menu> getPmenuList() {
		return pmenuList;
	}

	public void setPmenuList(List<Menu> pmenuList) {
		this.pmenuList = pmenuList;
	}

	public List<Menu> getCmenuList() {
		return cmenuList;
	}

	public void setCmenuList(List<Menu> cmenuList) {
		this.cmenuList = cmenuList;
	}

}
