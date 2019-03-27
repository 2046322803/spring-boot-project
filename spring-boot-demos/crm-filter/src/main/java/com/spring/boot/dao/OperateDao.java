package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spring.boot.entity.Operate;

public interface OperateDao extends JpaRepository<Operate, String>, JpaSpecificationExecutor<Operate> {

	List<Operate> findByParentIdIsNull();
	
	List<Operate> findByParentIdIsNotNull();
	
}
