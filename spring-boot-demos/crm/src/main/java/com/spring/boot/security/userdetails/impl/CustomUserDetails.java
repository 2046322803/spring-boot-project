package com.spring.boot.security.userdetails.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spring.boot.entity.Menu;
import com.spring.boot.entity.User;

@Component
public class CustomUserDetails implements UserDetails {

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

	public CustomUserDetails() {
	}

	public CustomUserDetails(User user, String roleName, List<Menu> pmenuList, List<Menu> cmenuList,
			Set<Object> operateSet) {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.roleName));
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPicture() {
		return picture;
	}

	public String getRoleName() {
		return roleName;
	}

	public List<Menu> getPmenuList() {
		return pmenuList;
	}

	public List<Menu> getCmenuList() {
		return cmenuList;
	}

	public Set<Object> getOperateSet() {
		return operateSet;
	}

	// 判断账号是否已经过期，默认没有过期
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/// 判断账号是否被锁定，默认没有锁定
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 判断信用凭证是否过期，默认没有过期
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 判断账号是否可用，默认可用
	@Override
	public boolean isEnabled() {
		return true;
	}

}
