package com.vt.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vt.spring.domain.Spittle;
import com.vt.spring.repository.SpittleRepository;

import exception.SpittleNotFoundException;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	@Autowired
	private SpittleRepository spittleRepository;
	
	@GetMapping
	public List<Spittle> showRecentSpittles(@RequestParam(value="count", defaultValue="20") int count){
		return spittleRepository.findRecentSpittles(count);
	}
	
	@GetMapping("/{id}")
	public String showRecentSpittles(@PathVariable long id, Model model){
		Spittle spittle = spittleRepository.findOne(id);
		if (spittle == null) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);
		return "spittle";
	}
	
	@ExceptionHandler(SpittleNotFoundException.class)
	public String duplicateSprittrHandler() {
		return "error/duplicate";
	}
}
