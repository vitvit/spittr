package com.vt.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.vt.spring.controller.HomeController;

import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class HomeControllerTest {

	private MockMvc mock;
	
	@Before
	public void setup(){
		mock = standaloneSetup(new HomeController()).build();
	}
	
	@Test
	public void testHome() throws Exception {
		mock.perform(get("/", "/home"))
			.andExpect(view().name("home"));
	}
}
