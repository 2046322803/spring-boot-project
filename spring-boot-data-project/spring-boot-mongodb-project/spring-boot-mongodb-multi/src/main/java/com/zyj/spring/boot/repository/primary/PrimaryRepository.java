package com.zyj.spring.boot.repository.primary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zyj.spring.boot.model.User;

/**
 * @author neo
 */
public interface PrimaryRepository extends MongoRepository<User, String> {
}
