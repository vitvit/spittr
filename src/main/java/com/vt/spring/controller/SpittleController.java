package com.vt.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import domain.Spittle;
import repository.SpittleRepository;

@Controller
public class SpittleController {

	@Autowired
	private SpittleRepository spittleRepository;
	
	@GetMapping("/spittles")
	public List<Spittle> showRecentSpittles(Model model){
		return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
	}
}
