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

import com.zuk.crm.entity.Operate;
import com.zuk.crm.service.OperateService;

@Controller
@RequestMapping("/system/operate")
public class OperateController {

	@Autowired
	private OperateService operateService;

	@RequestMapping("/list/{page}")
	public String listOperate(Model model, @PathVariable Integer page) {
		Sort sort = new Sort(Sort.Direction.ASC, "sort");
		page = page - 1 < 0 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(page, 10, sort);
		model.addAttribute("operatePage", operateService.listPage(pageable));
		return "system/operate/list";
	}

	@RequestMapping("/add")
	public String toAdd(Model model) {
		model.addAttribute("operate", new Operate());
		model.addAttribute("poList", operateService.listParent());
		return "system/operate/add";
	}

	@RequestMapping("/edit/{id}")
	public String toEdit(Model model, @PathVariable String id) {
		model.addAttribute("operate", operateService.get(id));
		model.addAttribute("poList", operateService.listParent());
		return "system/operate/edit";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("operate") Operate operate) {
		operateService.merge(operate);
		return "redirect:/system/operate/list/1";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute("operate") Operate operate) {
		operateService.merge(operate);
		return "redirect:/system/operate/list/1";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable String id) {
		operateService.delete(id);
		return "redirect:/system/operate/list/1";
	}

}
