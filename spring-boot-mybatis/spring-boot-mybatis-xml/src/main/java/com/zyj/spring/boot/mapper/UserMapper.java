package com.zyj.spring.boot.mapper;

import java.util.List;

import com.zyj.spring.boot.model.User;
import com.zyj.spring.boot.param.UserParam;

public interface UserMapper {

	List<User> getAll();

	List<User> getList(UserParam userParam);

	int getCount(UserParam userParam);

	User getOne(Long id);

	void insert(User user);

	int update(User user);

	int delete(Long id);

}