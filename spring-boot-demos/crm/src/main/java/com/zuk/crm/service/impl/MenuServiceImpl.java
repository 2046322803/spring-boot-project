package com.zuk.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.crm.dao.MenuDao;
import com.zuk.crm.entity.Menu;
import com.zuk.crm.service.MenuService;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public void init() {
		long count = menuDao.count();
		if (0L == count) {
			List<Menu> menuList = new ArrayList<Menu>(16);
			Menu menu1 = new Menu("1", "system", "system manage", "glyphicon glyphicon-cog", null, 10, null);
			Menu menu2 = new Menu("user", "user", null, "/system/user/list/1", 11, "1");
			Menu menu3 = new Menu("role", "role", null, "/system/role/list/1", 12, "1");
			Menu menu4 = new Menu("menu", "menu", null, "/system/menu/list/1", 13, "1");
			Menu menu5 = new Menu("operate", "operate", null, "/system/operate/list/1", 14, "1");

			menuList.add(menu1);
			menuList.add(menu2);
			menuList.add(menu3);
			menuList.add(menu4);
			menuList.add(menu5);

			menuDao.saveAll(menuList);
		}
	}

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
