package com.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import com.spring.boot.dao.RoleDao;
import com.spring.boot.dao.UserDao;
import com.spring.boot.entity.User;
import com.spring.boot.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

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

		Page<User> userPage = userDao.findAll(spec, pageable);

		List<User> userList = userPage.getContent();
		for (User user : userList) {
			user.setRoleName(roleDao.findById(user.getRoleId()).get().getName());
		}

		model.addAttribute("userPage", userPage);
	}

	@Override
	public void toAdd(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roleList", roleDao.findAll());
	}

	@Override
	public void toEdit(Model model, String id) {
		model.addAttribute("roleList", roleDao.findAll());
		// TODO findById(id).get()
		model.addAttribute("user", userDao.findById(id).get());
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
