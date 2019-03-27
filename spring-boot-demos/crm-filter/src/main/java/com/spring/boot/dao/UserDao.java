package com.spring.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spring.boot.entity.User;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
	User findByNameAndPassword(String name, String password);

}
