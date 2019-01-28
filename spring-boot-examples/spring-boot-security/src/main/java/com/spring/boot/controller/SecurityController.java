package com.spring.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping("/content")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String content() {
		return "content";
	}

	@RequestMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String admin() {
		return "admin";
	}
}