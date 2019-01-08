package com.zyj.spring.boot.repository.test2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyj.spring.boot.model.User;


public interface UserTest2Repository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}