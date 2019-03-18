package com.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dao.OperateDao;
import com.spring.boot.entity.Operate;
import com.spring.boot.service.OperateService;

@Service
@Transactional(rollbackFor = Exception.class)
public class OperateServiceImpl implements OperateService {

	@Autowired
	private OperateDao operateDao;

	@Override
	public void init() {
		long count = operateDao.count();
		if (0L == count) {
			List<Operate> operateList = new ArrayList<Operate>(16);
			Operate operate1 = new Operate("1", "user power", null, null, 100, null);
			Operate operate11 = new Operate("add", null, "/system/user/add", 101, "1");
			Operate operate12 = new Operate("delete", null, "/system/user/delete", 102, "1");
			Operate operate13 = new Operate("edit", null, "/system/user/eidt", 103, "1");
			Operate operate14 = new Operate("list", null, "/system/user/list", 104, "1");
			Operate operate15 = new Operate("save", null, "/system/user/save", 105, "1");
			Operate operate16 = new Operate("update", null, "/system/user/update", 106, "1");

			Operate operate2 = new Operate("2", "role power", null, null, 110, null);
			Operate operate21 = new Operate("add", null, "/system/role/add", 111, "2");
			Operate operate22 = new Operate("delete", null, "/system/role/delete", 112, "2");
			Operate operate23 = new Operate("edit", null, "/system/role/eidt", 113, "2");
			Operate operate24 = new Operate("list", null, "/system/role/list", 114, "2");
			Operate operate25 = new Operate("save", null, "/system/role/save", 115, "2");
			Operate operate26 = new Operate("update", null, "/system/role/update", 116, "2");

			Operate operate3 = new Operate("3", "menu power", null, null, 120, null);
			Operate operate31 = new Operate("add", null, "/system/menu/add", 121, "3");
			Operate operate32 = new Operate("delete", null, "/system/menu/delete", 122, "3");
			Operate operate33 = new Operate("edit", null, "/system/menu/eidt", 123, "3");
			Operate operate34 = new Operate("list", null, "/system/menu/list", 124, "3");
			Operate operate35 = new Operate("save", null, "/system/menu/save", 125, "3");
			Operate operate36 = new Operate("update", null, "/system/menu/update", 126, "3");

			Operate operate4 = new Operate("4", "operate power", null, null, 130, null);
			Operate operate41 = new Operate("add", null, "/system/operate/add", 131, "4");
			Operate operate42 = new Operate("delete", null, "/system/operate/delete", 132, "4");
			Operate operate43 = new Operate("edit", null, "/system/operate/eidt", 133, "4");
			Operate operate44 = new Operate("list", null, "/system/operate/list", 134, "4");
			Operate operate45 = new Operate("save", null, "/system/operate/save", 135, "4");
			Operate operate46 = new Operate("update", null, "/system/operate/update", 136, "4");

			operateList.add(operate1);
			operateList.add(operate11);
			operateList.add(operate12);
			operateList.add(operate13);
			operateList.add(operate14);
			operateList.add(operate15);
			operateList.add(operate16);
			operateList.add(operate2);
			operateList.add(operate21);
			operateList.add(operate22);
			operateList.add(operate23);
			operateList.add(operate24);
			operateList.add(operate25);
			operateList.add(operate26);
			operateList.add(operate3);
			operateList.add(operate31);
			operateList.add(operate32);
			operateList.add(operate33);
			operateList.add(operate34);
			operateList.add(operate35);
			operateList.add(operate36);
			operateList.add(operate4);
			operateList.add(operate41);
			operateList.add(operate42);
			operateList.add(operate43);
			operateList.add(operate44);
			operateList.add(operate45);
			operateList.add(operate46);

			operateDao.saveAll(operateList);
		}
	}

	@Override
	public Operate get(String id) {
		return operateDao.getOne(id);
	}

	@Override
	public void merge(Operate operate) {
		operateDao.save(operate);
	}

	@Override
	public void delete(String id) {
		operateDao.deleteById(id);
	}

	@Override
	public List<Operate> list() {
		return operateDao.findByOrderBySortAsc();
	}

	@Override
	public Page<Operate> listPage(Pageable pageable) {
		return operateDao.findAll(pageable);
	}

	@Override
	public List<Operate> listParent() {
		return operateDao.findByParentIdIsNull();
	}

}
