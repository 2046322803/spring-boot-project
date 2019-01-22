package com.zyj.spring.boot.mapper.two;

import java.util.List;

import com.zyj.spring.boot.model.User;

public interface User2Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}