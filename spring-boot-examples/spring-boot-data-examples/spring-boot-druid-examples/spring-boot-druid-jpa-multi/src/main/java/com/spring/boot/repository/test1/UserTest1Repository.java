package com.spring.boot.repository.test1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.model.User;

public interface UserTest1Repository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}