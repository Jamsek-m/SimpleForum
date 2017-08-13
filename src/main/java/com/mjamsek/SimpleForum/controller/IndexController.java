package com.mjamsek.SimpleForum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mjamsek.SimpleForum.entity.ForumPost;
import com.mjamsek.SimpleForum.entity.User;
import com.mjamsek.SimpleForum.service.ForumPostService;
import com.mjamsek.SimpleForum.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private ForumPostService forumPostService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String loadIndexPage(Model model) {
		ForumPost noviPost = new ForumPost();
		List<ForumPost> seznamNovic = forumPostService.vrniVsePoste();
		model.addAttribute("seznamNovic", seznamNovic);
		model.addAttribute("curr_user", userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("newForumPost", noviPost);
		model.addAttribute("unconfirmed_users", userService.vrniStNepotrjenih());
		return "index";
	}
	
	@PostMapping("/addNovica")
	public String addForumPost(@ModelAttribute(value="newForumPost") ForumPost forumPost, BindingResult bindingResult, Model model) {
		//get current user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();
		User currentLoggedUser = userService.findUserByUsername(currentUser);
		//set current user id
		forumPost.setAuthor(currentLoggedUser);
		//save to DB
		forumPostService.saveForumPost(forumPost);
		//send msg about success
		model.addAttribute("addNewForumPostMessage", "Novica je bila uspešno dodana!");
		//set data for index page
		model.addAttribute("newForumPost", new ForumPost());
		List<ForumPost> seznamNovic = forumPostService.vrniVsePoste();
		model.addAttribute("seznamNovic", seznamNovic);
		model.addAttribute("curr_user", userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("unconfirmed_users", userService.vrniStNepotrjenih());
		
		return "redirect:/";
	}
	
	@GetMapping("/isci")
	public String searchForPosts(@RequestParam("query") String query, Model model) {
		//init new post for data entry
		ForumPost noviPost = new ForumPost();
		model.addAttribute("newForumPost", noviPost);
		//list of all news
		List<ForumPost> seznamNovic = forumPostService.isciPoste(query);
		model.addAttribute("seznamNovic", seznamNovic);
		//get current user
		model.addAttribute("curr_user", userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		//get number of unconfirmed users
		model.addAttribute("unconfirmed_users", userService.vrniStNepotrjenih());
		
		return "index";
	}
	
}
