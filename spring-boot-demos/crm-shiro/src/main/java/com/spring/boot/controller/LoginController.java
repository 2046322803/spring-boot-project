package com.spring.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password, @RequestParam(required = false) boolean rememberMe) {
		try {
			ByteSource salt = new Md5Hash(username);
			password = new SimpleHash("MD5", password, salt).toString();

			UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);
			upToken.setRememberMe(rememberMe);

			Subject subject = SecurityUtils.getSubject();
			subject.login(upToken);
			subject.getSession().setAttribute("aclBean", (ShiroUser) subject.getPrincipal());
		} catch (UnknownAccountException e) {
			return "wrong";
		} catch (IncorrectCredentialsException e) {
			return "wrong";
		} catch (Exception e) {
			return "failure";
		}

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
	public String logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		// 清除缓存
		subject.logout();
		return "redirect:/login";
	}

}
