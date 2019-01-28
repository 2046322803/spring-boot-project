package com.spring.boot.repository;

import java.util.List;

import com.spring.boot.model.User;

public interface UserRepository  {

    int save(User user);

    int update(User user);

    int delete(long id);

    List<User> findALL();

    User findById(long id);
}