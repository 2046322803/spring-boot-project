package com.spring.boot.repository.secondary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.model.User;

/**
 * @author neo
 */
public interface SecondaryRepository extends MongoRepository<User, String> {
}
