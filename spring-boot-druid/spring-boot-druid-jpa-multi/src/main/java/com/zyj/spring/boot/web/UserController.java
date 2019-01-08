package com.zyj.spring.boot.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyj.spring.boot.model.User;
import com.zyj.spring.boot.repository.test1.UserTest1Repository;
import com.zyj.spring.boot.repository.test2.UserTest2Repository;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserTest1Repository userTest1Repository;
	@Autowired
	private UserTest2Repository userTest2Repository;

	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users1=userTest1Repository.findAll();
		List<User> users2=userTest2Repository.findAll();
		users1.addAll(users2);
		return users1;
	}


    
}