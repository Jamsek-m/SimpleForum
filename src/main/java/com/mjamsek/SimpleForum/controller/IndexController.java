package com.mjamsek.SimpleForum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String loadIndexPage() {
		return "index";
	}
	
}
