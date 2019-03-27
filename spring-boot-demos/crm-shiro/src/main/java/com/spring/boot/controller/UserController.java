package com.spring.boot.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.entity.User;
import com.spring.boot.service.UserService;
import com.zuk.storage.cos.service.FileStorageService;

@Controller
@RequestMapping("/system/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping("/list/{page}")
	public String list(Model model, @PathVariable Integer page, @RequestParam(required = false) String roleId,
			@RequestParam(required = false) String name) {
		userService.listPage(model, page, roleId, name);
		return "system/user/list";
	}

	@RequestMapping("/add")
	public String toAdd(Model model) {
		userService.toAdd(model);
		return "system/user/add";
	}

	@RequestMapping("/edit/{id}")
	public String toEdit(Model model, @PathVariable String id) {
		userService.toEdit(model, id);
		return "system/user/edit";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file) {
		String picture = "";
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String key = System.currentTimeMillis() + file.getOriginalFilename();
				ByteArrayInputStream is = new ByteArrayInputStream(bytes);
				long contentLength = is.available();
				picture = fileStorageService.upload(key, is, contentLength);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		user.setPicture(picture);
		
		ByteSource salt = new Md5Hash(user.getName());
		String password = new SimpleHash("MD5", user.getPassword(), salt).toString();
		user.setPassword(password);
		
		userService.merge(user);
		return "redirect:/system/user/list/1";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String picture = "";
			try {
				byte[] bytes = file.getBytes();
				String key = System.currentTimeMillis() + file.getOriginalFilename();
				ByteArrayInputStream is = new ByteArrayInputStream(bytes);
				long contentLength = is.available();
				picture = fileStorageService.upload(key, is, contentLength);
			} catch (IOException e) {
				e.printStackTrace();
			}
			user.setPicture(picture);
		}
		userService.merge(user);
		return "redirect:/system/user/list/1";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable String id) {
		userService.delete(id);
		return "redirect:/system/user/list/1";
	}

}
