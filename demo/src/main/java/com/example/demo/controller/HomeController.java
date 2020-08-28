package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	private UserService userService;
	public HomeController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String getHome(Model model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "index";
	}
	
	@GetMapping("/create")
	public String getForm(Model model) {
		User newUser = new User();
		model.addAttribute("title", "Create Form");
		model.addAttribute("user", newUser);
		return "form";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("user") User user) {
		userService.createOrUpdateUser(user);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("title", "Edit Form");
		model.addAttribute("user", user);
		return "form";
	}
	
	@PostMapping("/delete/{id}")
	public String remove(@PathVariable(value="id") Long id, Model model) {
		userService.removeUser(id);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}
}
