package com.spring.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.entity.Operate;
import com.spring.boot.entity.RoleOperate;

public interface RoleOperateDao extends JpaRepository<RoleOperate, String>, JpaSpecificationExecutor<RoleOperate> {

	@Query("select o from Operate o, RoleOperate ro where o.id = ro.pk.operateId and ro.pk.roleId = :roleId")
	List<Operate> queryOperateByRoleId(@Param("roleId") String roleId);

	@Query("select ro from RoleOperate ro where ro.pk.roleId = :roleId")
	List<RoleOperate> queryByRoleId(@Param("roleId") String roleId);

	@Modifying
	@Transactional
	@Query("delete from RoleOperate ro where ro.pk.roleId = :roleId")
	void deleteByRoleId(@Param("roleId") String roleId);

}
