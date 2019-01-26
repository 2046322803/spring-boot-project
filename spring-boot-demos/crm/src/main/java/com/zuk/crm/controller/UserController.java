package com.zuk.crm.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zuk.crm.entity.User;
import com.zuk.crm.service.UserService;
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

				// picture = UPLOADED_FOLDER + file.getOriginalFilename();
				// Path path = Paths.get(picture);
				// Files.write(path, bytes);

				String key = System.currentTimeMillis() + file.getOriginalFilename();
				ByteArrayInputStream is = new ByteArrayInputStream(bytes);
				long contentLength = is.available();
				picture = fileStorageService.upload(key, is, contentLength);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		user.setPicture(picture);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.merge(user);
		return "redirect:/system/user/list/1";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute("user") User user) {
		userService.merge(user);
		return "redirect:/system/user/list/1";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable String id) {
		userService.delete(id);
		return "redirect:/system/user/list/1";
	}

}
