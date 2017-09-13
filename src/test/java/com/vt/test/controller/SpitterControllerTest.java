package com.vt.test.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.vt.spring.controller.SpitterController;
import com.vt.spring.domain.Spitter;
import com.vt.spring.repository.SpitterRepository;

public class SpitterControllerTest {

	private MockMvc mock;
	@Mock
	private SpitterRepository spitterRepository;	
	@InjectMocks
	private SpitterController spitterController;	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mock = standaloneSetup(spitterController).build();
	}
	
	@Test
	public void testShowRegistrationForm() throws Exception {
		mock.perform(get("/spitter/register"))
			.andExpect(view().name("registerForm"));
	}
	
	@Test
	public void testProcessRegistrationSuccess() throws Exception {
		Spitter newSpitter = new Spitter("testspitter", "testpass", "test", "spitter");
		Spitter savedSpitter = new Spitter(24L, "testspitter", "testpass", "test", "spitter");
		
		when(spitterRepository.save(newSpitter)).thenReturn(savedSpitter);
				
		mock.perform(post("/spitter/register")
						.param("username", "testspitter")
						.param("password", "testpass")
						.param("firstname", "test")
						.param("lastname", "spitter"))
			.andExpect(redirectedUrl("/spitter/testspitter"));
		
		verify(spitterRepository, atLeastOnce()).save(newSpitter);
	}
}
