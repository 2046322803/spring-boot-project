package com.spring.boot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.entity.Operate;

public interface OperateService {

	Operate get(String id);

	void merge(Operate operate);

	void delete(String id);

	List<Operate> list();

	Page<Operate> listPage(Pageable pageable);

	List<Operate> listParent();

}
