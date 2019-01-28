package com.spring.boot.repository.primary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.model.User;

/**
 * @author neo
 */
public interface PrimaryRepository extends MongoRepository<User, String> {
}
