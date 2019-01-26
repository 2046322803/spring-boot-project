package com.zuk.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/wrong")
	public String wrong(ModelMap model) {
		model.addAttribute("errorMsg", "用户名或密码输入错误");
		return "login";
	}

	@RequestMapping("/forbid")
	public String forbid(ModelMap model) {
		model.addAttribute("errorMsg", "账户被禁用");
		return "login";
	}

	@RequestMapping("/failure")
	public String failure(ModelMap model) {
		model.addAttribute("errorMsg", "登录失败");
		return "login";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}

}
