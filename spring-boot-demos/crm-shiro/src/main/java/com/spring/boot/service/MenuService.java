package com.spring.boot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.entity.Menu;

public interface MenuService {

	void init();
	
	Menu get(String id);
	
	void merge(Menu menu);
	
	void delete(String id);
	
	List<Menu> list();

	Page<Menu> listPage(Pageable pageable);

	List<Menu> listParent();
	
}
