package com.spring.boot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mapper.one.UserOneMapper;
import com.spring.boot.mapper.two.UserTwoMapper;
import com.spring.boot.model.User;
import com.spring.boot.param.UserParam;
import com.spring.boot.result.Page;

@RestController
public class UserController {
	
	@Autowired
	private UserOneMapper userOneMapper;

    @Autowired
    private UserTwoMapper userTwoMapper;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
        List<User> usersOne=userOneMapper.getAll();
        List<User> usersTwo=userTwoMapper.getAll();
        usersOne.addAll(usersTwo);
		return usersOne;
	}

    @RequestMapping("/getList")
    public Page<User> getList(UserParam userParam) {
        List<User> users=userOneMapper.getList(userParam);
        long count=userOneMapper.getCount(userParam);
        Page page = new Page(userParam,count,users);
        return page;
    }

    @RequestMapping("/getUser")
    public User getUser(Long id) {
    	User user=userTwoMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(User user) {
        userOneMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(User user) {
        userOneMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userTwoMapper.delete(id);
    }
    
    
}