package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spring.boot.entity.Menu;

public interface MenuDao extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

	List<Menu> findByParentIdIsNull();
	
}
