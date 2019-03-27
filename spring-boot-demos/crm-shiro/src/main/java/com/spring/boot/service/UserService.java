package com.spring.boot.service;

import java.io.OutputStream;

import org.springframework.ui.Model;

import com.spring.boot.entity.User;

public interface UserService {

	void init();

	void listPage(Model model, Integer page, String roleId, String name);

	void toAdd(Model model);

	void toEdit(Model model, String id);

	void merge(User user);

	void delete(String id);

	void export(String roleId, String name, OutputStream os);

}
