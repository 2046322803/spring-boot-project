package com.spring.boot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.shiro.ShiroUser;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		System.out.println("4");
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam(required = false) String name, @RequestParam(required = false) String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken upToken = new UsernamePasswordToken(name, password);
		subject.login(upToken);
		subject.getSession().setAttribute("aclBean", (ShiroUser) subject.getPrincipal());
		return "redirect:/home";
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
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().removeAttribute("aclBean");
		// 清除缓存
		subject.logout();
		return "redirect:/login";
	}

}
