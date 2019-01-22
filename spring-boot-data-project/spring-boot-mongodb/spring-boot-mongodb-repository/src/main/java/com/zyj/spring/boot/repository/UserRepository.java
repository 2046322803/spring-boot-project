package com.zyj.spring.boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.zyj.spring.boot.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUserName(String userName);
    Page<User> findAll(Pageable var1);
}