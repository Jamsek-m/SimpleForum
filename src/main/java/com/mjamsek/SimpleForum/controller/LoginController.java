package com.mjamsek.SimpleForum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjamsek.SimpleForum.entity.User;
import com.mjamsek.SimpleForum.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loadLoginPage(Model model) {
		return "login/login-page";
	}
	
	@GetMapping("/registration")
	public String loadRegistrationPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login/registration";
	}
	
	@PostMapping("/registration")
	public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User obstojeciUser = userService.findUserByUsername(user.getUsername());
		if(obstojeciUser != null) {
			bindingResult.rejectValue("username", "USername is taken!");
		}
		if(bindingResult.hasErrors()) {
			return "login/registration";
		} else {
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been successfully registered!");
			model.addAttribute("user", new User());
			return "login/registration";
		}
	}
	
	@RequestMapping("/access-denied")
	public String loadAccessDeniedPage() {
		return "login/access-denied";
	}
	
}
