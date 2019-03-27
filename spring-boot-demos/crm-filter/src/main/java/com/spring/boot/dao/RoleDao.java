package com.spring.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spring.boot.entity.Role;

public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

	Role findByCode(String code);

}
