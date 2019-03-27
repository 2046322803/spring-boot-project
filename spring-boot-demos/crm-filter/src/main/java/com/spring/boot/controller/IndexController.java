package com.spring.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.bean.AclBean;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(HttpSession session) {
		AclBean aclBean = (AclBean) session.getAttribute("aclBean");
		if (aclBean == null) {
			return "login";
		} else {
			return "index";
		}
	}

	@RequestMapping("/home")
	public String home(HttpSession session) {
		AclBean aclBean = (AclBean) session.getAttribute("aclBean");
		if (aclBean == null) {
			return "login";
		} else {
			return "index";
		}
	}

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/about")
	public String toAbout(HttpSession session) {
		AclBean aclBean = (AclBean) session.getAttribute("aclBean");
		if (aclBean == null) {
			return "login";
		} else {
			return "about";
		}
	}

	@RequestMapping("/warn")
	public String toWarn(HttpSession session) {
		AclBean aclBean = (AclBean) session.getAttribute("aclBean");
		if (aclBean == null) {
			return "login";
		} else {
			return "warn";
		}
	}

}
