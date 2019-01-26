package com.zuk.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zuk.crm.entity.Operate;

public interface OperateDao extends JpaRepository<Operate, String>, JpaSpecificationExecutor<Operate> {

	List<Operate> findByParentIdIsNull();

	List<Operate> findByParentIdIsNotNull();

	Operate findByHref(String href);

	List<Operate> findByOrderBySortAsc();
	
	List<Operate> findByParentIdIsNotNullOrderBySortAsc();

}
