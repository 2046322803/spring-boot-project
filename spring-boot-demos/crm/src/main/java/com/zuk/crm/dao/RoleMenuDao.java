package com.zuk.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zuk.crm.entity.Menu;
import com.zuk.crm.entity.RoleMenu;

public interface RoleMenuDao extends JpaRepository<RoleMenu, String>, JpaSpecificationExecutor<RoleMenu> {

	@Query("select m from Menu m, RoleMenu rm where m.id = rm.pk.menuId and rm.pk.roleId = :roleId")
	List<Menu> queryMenuByRoleId(@Param("roleId") String roleId);

	@Query("select rm from RoleMenu rm where rm.pk.roleId = :roleId")
	List<RoleMenu> queryByRoleId(@Param("roleId") String roleId);

	@Modifying
	@Transactional
	@Query("delete from RoleMenu rm where rm.pk.roleId = :roleId")
	void deleteByRoleId(@Param("roleId") String roleId);

}
