package com.zuk.crm.service;

import org.springframework.ui.Model;

import com.zuk.crm.bean.AclBean;
import com.zuk.crm.entity.User;

public interface UserService {

	void init();

	AclBean validate(User user);

	void listPage(Model model, Integer page, String roleId, String name);

	void toAdd(Model model);

	void toEdit(Model model, String id);

	void merge(User user);

	void delete(String id);

}
