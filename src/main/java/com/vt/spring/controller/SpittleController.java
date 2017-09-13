package com.vt.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vt.spring.domain.Spittle;
import com.vt.spring.repository.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807"; 
	@Autowired
	private SpittleRepository spittleRepository;
	
	@GetMapping
	public List<Spittle> showRecentSpittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
											@RequestParam(value="count", defaultValue="20") int count){
		return spittleRepository.findSpittles(max, count);
	}
	
	@GetMapping("/{id}")
	public String showRecentSpittles(@PathVariable long id, Model model){
		model.addAttribute(spittleRepository.findOne(id));
		return "spittle";
	}
}
