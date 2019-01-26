package com.zuk.crm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zuk.crm.entity.Role;

public interface RoleService {

	void init();
	
	void merge(Role role);
	
	void delete(String id);
	
	Role get(String id);
	
	List<Role> list();

	Page<Role> listPage(Pageable pageable);

}
