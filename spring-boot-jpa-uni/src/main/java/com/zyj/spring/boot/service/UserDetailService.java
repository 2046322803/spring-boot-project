package com.zyj.spring.boot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zyj.spring.boot.model.UserDetail;
import com.zyj.spring.boot.param.UserDetailParam;

public interface UserDetailService {
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
