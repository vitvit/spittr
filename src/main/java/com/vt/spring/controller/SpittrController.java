package com.vt.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpittrController {

	@GetMapping("/")
	public String home(Model model){
		return "home";
	}
}
