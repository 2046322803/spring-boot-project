package com.zyj.spring.boot.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyj.spring.boot.model.User;
import com.zyj.spring.boot.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users=userRepository.findAll();
		return users;
	}


    
}