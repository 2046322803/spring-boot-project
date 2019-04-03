package com.spring.boot.service.impl;

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
		return operateDao.findAll();
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
