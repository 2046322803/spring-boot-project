package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.domain.Comment;

/**
 * Comment Repository 接口. 
 * 
 * @since 1.0.0 2017年6月6日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
