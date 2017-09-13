package com.vt.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vt.spring.domain.Spitter;
import com.vt.spring.repository.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	@Autowired
	private SpitterRepository spitterRepository;
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		return "registerForm";
	}
	
	@PostMapping("/register")
	public String processRegistration(Spitter spitter) {
		spitterRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}
}
