package com.zuk.crm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.zuk.crm.bean.AclBean;
import com.zuk.crm.dao.MenuDao;
import com.zuk.crm.dao.OperateDao;
import com.zuk.crm.dao.RoleDao;
import com.zuk.crm.dao.RoleMenuDao;
import com.zuk.crm.dao.RoleOperateDao;
import com.zuk.crm.dao.UserDao;
import com.zuk.crm.entity.Menu;
import com.zuk.crm.entity.Operate;
import com.zuk.crm.entity.Role;
import com.zuk.crm.entity.User;
import com.zuk.crm.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private OperateDao operateDao;

	@Autowired
	private RoleMenuDao roleMenuDao;

	@Autowired
	private RoleOperateDao roleOperateDao;

	@Override
	public void init() {
		long count = userDao.count();
		if (0L == count) {
			User user = new User();
			user.setName("super");
			user.setPassword("123456");
			user.setEmail("super@bsp.com");
			String roleId = roleDao.findByCode("SUPER").getId();
			user.setRoleId(roleId);
			user = userDao.save(user);
		}
	}

	@Override
	public AclBean validate(User user) {
		User ruser = userDao.findByNameAndPassword(user.getName(), user.getPassword());
		if (ruser == null) {
			return null;
		} else {
			AclBean aclBean = new AclBean();
			aclBean.setUserId(ruser.getId());
			aclBean.setUserName(ruser.getName());
			aclBean.setPassword(ruser.getPassword());
			aclBean.setPicture(ruser.getPicture());
			Role role = roleDao.getOne(ruser.getRoleId());
			String roleName = role.getCode();
			aclBean.setRoleName(roleName);

			List<Menu> pmenuList = new ArrayList<Menu>();
			List<Menu> cmenuList = new ArrayList<Menu>();
			Set<Object> operateSet = new HashSet<Object>();
			if ("SUPER".equals(roleName)) {
				List<Menu> menuList = menuDao.findAll();
				for (Menu menu : menuList) {
					if (menu.getParentId() == null || "".equals(menu.getParentId())) {
						pmenuList.add(menu);
					} else {
						cmenuList.add(menu);
					}
				}
				List<Operate> operateList = operateDao.findAll();
				for (Operate operate : operateList) {
					operateSet.add(operate.getHref());
				}
			} else {
				List<Menu> menuList = roleMenuDao.queryMenuByRoleId(role.getId());
				for (Menu menu : menuList) {
					if (StringUtils.isEmpty(menu.getParentId())) {
						pmenuList.add(menu);
					} else {
						cmenuList.add(menu);
					}
				}
				List<Operate> operateList = roleOperateDao.queryOperateByRoleId(role.getId());
				for (Operate operate : operateList) {
					operateSet.add(operate.getHref());
				}
			}
			aclBean.setPmenuList(pmenuList);
			aclBean.setCmenuList(cmenuList);
			aclBean.setOperateSet(operateSet);
			return aclBean;
		}
	}

	@Override
	public void listPage(Model model, Integer page, String roleId, String name) {
		model.addAttribute("roleList", roleDao.findAll());

		Specification<User> spec = new Specification<User>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(roleId)) {
					Predicate roleIdPredicate = cb.equal(root.get("roleId").as(String.class), roleId);
					predicateList.add(roleIdPredicate);
				}

				if (!StringUtils.isEmpty(name)) {
					Predicate namePredicate = cb.like(root.get("name").as(String.class), "%" + name + "%");
					predicateList.add(namePredicate);
				}

				Predicate[] predicateArray = new Predicate[predicateList.size()];
				return cb.and(predicateList.toArray(predicateArray));
			}
		};

		Sort sort = new Sort(Sort.Direction.DESC, "id");
		page = page - 1 < 0 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(page, 10, sort);

		model.addAttribute("userPage", userDao.findAll(spec, pageable));
	}

	@Override
	public void toAdd(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roleList", roleDao.findAll());
	}

	@Override
	public void toEdit(Model model, String id) {
		model.addAttribute("roleList", roleDao.findAll());
		model.addAttribute("user", userDao.findById(id));
	}

	@Override
	public void merge(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(String id) {
		userDao.deleteById(id);
	}

}
