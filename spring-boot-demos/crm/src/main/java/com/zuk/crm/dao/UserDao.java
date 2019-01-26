package com.zuk.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.crm.entity.User;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
	User findByNameAndPassword(String name, String password);
	
	User findByName(String name);

}
