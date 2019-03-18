package com.spring.boot.shiro;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.spring.boot.entity.Menu;
import com.spring.boot.entity.User;

public class ShiroUser implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	private String userId;
	private String userName;
	private String password;
	private String picture;
	private String roleName;
	private List<Menu> pmenuList;
	private List<Menu> cmenuList;
	private Set<Object> operateSet;

	public ShiroUser() {
	}

	public ShiroUser(User user, String roleName, List<Menu> pmenuList, List<Menu> cmenuList, Set<Object> operateSet) {
		this.username = user.getName();

		this.userId = user.getId();
		this.userName = user.getName();
		this.password = user.getPassword();
		this.picture = user.getPicture();
		this.roleName = roleName;
		this.pmenuList = pmenuList;
		this.cmenuList = cmenuList;
		this.operateSet = operateSet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public Set<Object> getOperateSet() {
		return operateSet;
	}

	public void setOperateSet(Set<Object> operateSet) {
		this.operateSet = operateSet;
	}

}
