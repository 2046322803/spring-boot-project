package com.zyj.spring.boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.zyj.spring.boot.model.User;

import java.util.Optional;

public interface UserRepository  extends MongoRepository<User, String> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(String id);
    User findByUserNameOrEmail(String userName, String email);
    User findByUserName(String userName);
    User findByEmail(String email);
    void deleteById(String id);
}