package com.vt.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vt.spring.domain.Spitter;
import com.vt.spring.repository.SpitterRepository;

import exception.DuplicateSpittrException;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	@Autowired
	private SpitterRepository spitterRepository;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}

	@PostMapping("/register")
	public String processRegistration(@Valid Spitter spitter, Errors errors,
			RedirectAttributes redirectAttributes) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		if (spitterRepository.findByUsername(spitter.getUsername()) != null){
			throw new DuplicateSpittrException();
		}
		spitterRepository.save(spitter);
		redirectAttributes.addAttribute("username", spitter.getUsername());
		redirectAttributes.addFlashAttribute(spitter);
		return "redirect:/spitter/{username}";
	}

	@GetMapping("/{username}")
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if (!model.containsAttribute("spitter")) {
			model.addAttribute(spitterRepository.findByUsername(username));
		}
		return "profile";
	}
}
