package com.spring.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dao.MenuDao;
import com.spring.boot.entity.Menu;
import com.spring.boot.service.MenuService;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public Menu get(String id) {
		return menuDao.getOne(id);
	}

	@Override
	public void merge(Menu menu) {
		menuDao.save(menu);
	}

	@Override
	public void delete(String id) {
		menuDao.deleteById(id);
	}

	@Override
	public List<Menu> list() {
		return menuDao.findAll();
	}

	@Override
	public Page<Menu> listPage(Pageable pageable) {
		return menuDao.findAll(pageable);
	}

	@Override
	public List<Menu> listParent() {
		return menuDao.findByParentIdIsNull();
	}

}
