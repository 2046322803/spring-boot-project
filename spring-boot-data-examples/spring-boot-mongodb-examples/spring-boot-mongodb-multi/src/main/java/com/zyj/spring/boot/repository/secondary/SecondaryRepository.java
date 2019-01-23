package com.zyj.spring.boot.repository.secondary;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zyj.spring.boot.model.User;

/**
 * @author neo
 */
public interface SecondaryRepository extends MongoRepository<User, String> {
}
