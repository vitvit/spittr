package com.vt.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.vt.spring.domain.Spittle;
import com.vt.spring.repository.SpittleRepository;

@Controller
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807"; 
	@Autowired
	private SpittleRepository spittleRepository;
	
	@GetMapping("/spittles")
	public List<Spittle> showRecentSpittles(@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
											@RequestParam(value="count", defaultValue="20") int count){
		return spittleRepository.findSpittles(max, count);
	}
	
	@GetMapping("/spittle/{id}")
	public List<Spittle> showRecentSpittles(@RequestAttribute long id){
		return null;//spittleRepository.findOne(id);
	}
}
