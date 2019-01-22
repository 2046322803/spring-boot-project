package com.zyj.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyj.spring.boot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}