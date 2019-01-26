package com.zuk.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zuk.crm.entity.Role;
import com.zuk.crm.service.MenuService;
import com.zuk.crm.service.OperateService;
import com.zuk.crm.service.RoleMenuService;
import com.zuk.crm.service.RoleOperateService;
import com.zuk.crm.service.RoleService;

@Controller
@RequestMapping("/system/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private OperateService operateService;

	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	private RoleOperateService roleOperateService;

	@RequestMapping("/list/{page}")
	public String list(Model model, @PathVariable Integer page) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		page = page - 1 < 0 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(page, 10, sort);
		model.addAttribute("rolePage", roleService.listPage(pageable));
		return "system/role/list";
	}

	@RequestMapping("/add")
	public String toAdd(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("menuList", menuService.list());
		model.addAttribute("operateList", operateService.list());
		return "system/role/add";
	}

	@RequestMapping("/edit/{id}")
	public String toEdit(Model model, @PathVariable String id) {
		model.addAttribute("role", roleService.get(id));
		model.addAttribute("menuList", menuService.list());
		model.addAttribute("rmList", roleMenuService.list(id));
		model.addAttribute("operateList", operateService.list());
		model.addAttribute("roList", roleOperateService.list(id));
		return "system/role/edit";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("role") Role role, @RequestParam("menus") String[] menus,
			@RequestParam("operates") String[] operates) {
		roleService.merge(role);
		roleMenuService.save(role, menus);
		roleOperateService.save(role, operates);
		return "redirect:/system/role/list/1";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute("role") Role role, @RequestParam("menus") String[] menus,
			@RequestParam("operates") String[] operates) {
		roleService.merge(role);
		roleMenuService.update(role, menus);
		roleOperateService.update(role, operates);
		return "redirect:/system/role/list/1";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable String id) {
		roleService.delete(id);
		roleMenuService.deleteByRoleId(id);
		return "redirect:/system/role/list/1";
	}

}
