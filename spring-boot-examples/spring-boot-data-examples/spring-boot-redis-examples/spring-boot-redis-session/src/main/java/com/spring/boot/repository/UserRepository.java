package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}