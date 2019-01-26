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

import com.zuk.crm.entity.Menu;
import com.zuk.crm.service.MenuService;

@Controller
@RequestMapping("/system/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/list/{page}")
	public String list(Model model, @PathVariable Integer page) {
		Sort sort = new Sort(Sort.Direction.ASC, "sort");
		page = page - 1 < 0 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(page, 10, sort);
		model.addAttribute("menuPage", menuService.listPage(pageable));
		return "system/menu/list";
	}

	@RequestMapping("/add")
	public String toAdd(Model model) {
		model.addAttribute("menu", new Menu());
		model.addAttribute("pmList", menuService.listParent());
		return "system/menu/add";
	}

	@RequestMapping("/edit/{id}")
	public String toEdit(Model model, @PathVariable String id) {
		model.addAttribute("pmList", menuService.listParent());
		model.addAttribute("menu", menuService.get(id));
		return "system/menu/edit";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("menu") Menu menu) {
		menuService.merge(menu);
		return "redirect:/system/menu/list/1";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute("menu") Menu menu) {
		menuService.merge(menu);
		return "redirect:/system/menu/list/1";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable String id) {
		menuService.delete(id);
		return "redirect:/system/menu/list/1";
	}

}
