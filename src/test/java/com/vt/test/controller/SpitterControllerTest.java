package com.vt.test.controller;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.vt.spring.controller.AppWideExceptionHandler;
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
		mock = standaloneSetup(spitterController)
				.setControllerAdvice(new AppWideExceptionHandler())
				.build();
	}
	
	@Test
	public void testShowRegistrationForm() throws Exception {
		mock.perform(get("/spitter/register"))
			.andExpect(view().name("registerForm"));
	}
	
	@Test
	public void testProcessRegistrationSuccess() throws Exception {
		mock.perform(post("/spitter/register")
						.param("firstname", "test")
						.param("lastname", "spitter")
						.param("username", "testspitter")
						.param("password", "testpass"))
			.andExpect(redirectedUrl("/spitter/testspitter"));
	}
	
	@Test
	public void testShowSpitterProfile() throws Exception {
		Spitter expectedSpitter = new Spitter(333, "testname", "password ", "firstname", "lastname");
		
		when(spitterRepository.findByUsername("testname")).thenReturn(expectedSpitter);
		
		mock.perform(get("/spitter/{username}", "testname"))
			.andExpect(view().name("profile"))
			.andExpect(model().attributeExists("spitter"))
			.andExpect(model().attribute("spitter", expectedSpitter));
	}
	
    @Test
    public void testProcessRegistrationDuplicateFoundFault() throws Exception {
		when(spitterRepository.findByUsername("testspitter")).thenReturn(new Spitter(333, "testspitter", "testpass ", "test", "spitter"));
		mock.perform(post("/spitter/register")
				.param("firstname", "test")
				.param("lastname", "spitter")
				.param("username", "testspitter")
				.param("password", "testpass"))
		 	.andExpect(view().name("error/duplicate"));
    }
}
