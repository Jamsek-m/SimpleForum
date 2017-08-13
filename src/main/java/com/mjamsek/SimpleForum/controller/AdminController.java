package com.mjamsek.SimpleForum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mjamsek.SimpleForum.controller.wrapper.EditUserWrapper;
import com.mjamsek.SimpleForum.entity.Role;
import com.mjamsek.SimpleForum.entity.User;
import com.mjamsek.SimpleForum.service.RoleService;
import com.mjamsek.SimpleForum.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@GetMapping("/home")
	public String loadAdminHomePage(Model model) {
		//get current user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		model.addAttribute("curr_user", user);
		
		//get list of users
		List<User> seznamUporabnikov = userService.findAll();
		model.addAttribute("seznamUpor", seznamUporabnikov);
		
		List<User> seznamNepotrjenih = userService.vrniVseNepotrjeneUporabnike();
		model.addAttribute("seznamNepot", seznamNepotrjenih);
		
		return "admin/home";
	}
	
	@GetMapping("/spremeniSelf")
	public String loadEditSelfUserPage(Model model) {
		//get current user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		model.addAttribute("user", user);
		//get all roles
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		//is editing self
		model.addAttribute("self", true);
		//wrapper for incoming data
		EditUserWrapper userWrapper = new EditUserWrapper();
		userWrapper.setId(user.getId());
		userWrapper.setDisplayName(user.getDisplayName());
		userWrapper.setUsername(user.getUsername());
		model.addAttribute("userWrapper", userWrapper);
		
		return "admin/edit";
	}
	
	@PostMapping("/spremeniSelf")
	public String editSelfUser(@ModelAttribute(value="userWrapper") EditUserWrapper editUserWrapper) {
		userService.editUser(editUserWrapper);
		if(editUserWrapper.getPassword() == null || editUserWrapper.getPassword().equals("")) {
			return "redirect:/admin/home";
		}
		return "redirect:/logout";
	}
	
	@PostMapping("/spremeni")
	public String editUser(@ModelAttribute(value="userWrapper") EditUserWrapper editUserWrapper) {
		userService.editUser(editUserWrapper);
		return "redirect:/admin/home";
	}

	@GetMapping("/spremeni/{id}")
	public String loadEditUserPage(@PathVariable("id") int id, Model model) {
		//get user
		User user = userService.findById(id);
		model.addAttribute("user", user);
		//get roles
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		//is editing self
		model.addAttribute("self", false);
		//wrapper for incoming data
		EditUserWrapper userWrapper = new EditUserWrapper();
		userWrapper.setId(user.getId());
		userWrapper.setDisplayName(user.getDisplayName());
		userWrapper.setUsername(user.getUsername());
		model.addAttribute("userWrapper", userWrapper);
		
		return "admin/edit";
	}
	
	@GetMapping("/potrdi/{id}")
	public String potrdiUporabnika(@PathVariable("id") int id) {
		userService.potrdiUporabnika(id);
		return "redirect:/admin/home";
	}
	
	@GetMapping("/disable/{id}")
	public String disableUser(@PathVariable("id") int id) {
		userService.disableUser(id);
		return "redirect:/admin/home";
	}
	
}
