package com.zuk.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/about")
	public String toAbout(HttpSession session) {
		return "about";
	}

	@RequestMapping("/warn")
	public String toWarn(HttpSession session) {
		return "warn";
	}

}
