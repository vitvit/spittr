package com.vt.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import com.vt.spring.controller.SpittleController;
import com.vt.spring.domain.Spitter;
import com.vt.spring.domain.Spittle;
import com.vt.spring.repository.SpittleRepository;

public class SpittleControllerTest {

	private MockMvc mock;
	@Mock
	private SpittleRepository spittleRepository;	
	@InjectMocks
	private SpittleController spittleController;	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mock = standaloneSetup(spittleController)
			.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
	}
	
	@Test
	public void testShowRecentSpittlesSuccess() throws Exception {
		List<Spittle> expectedSpittleList = createSpittleList(20);
		
		when(spittleRepository.findRecentSpittles(20)).thenReturn(expectedSpittleList);
		mock.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittleList.toArray())));
	}

	@Test
	public void testShowPagedRecentSpittlesSuccess() throws Exception {
		List<Spittle> expectedSpittleList = createSpittleList(22);
		
		when(spittleRepository.findRecentSpittles(22)).thenReturn(expectedSpittleList);
		mock.perform(get("/spittles?count=22"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittleList.toArray())));
	}
	
	@Test
	public void testShowSpittleSuccess() throws Exception {		
		Spittle expectedSpittle = new Spittle("Test spittle", new Date(), new Spitter());
		expectedSpittle.setId(1L);
		when(spittleRepository.findOne(1)).thenReturn(expectedSpittle);
		mock.perform(get("/spittles/{id}", 1))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", expectedSpittle));
	}
	
	@Test
	public void testShowSpittleNotFoundFault() throws Exception {		
		when(spittleRepository.findOne(1)).thenReturn(null);
		mock.perform(get("/spittles/{id}", 1))
			.andExpect(view().name("error/duplicate"));
	}
	
	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (int i = 0; i < count; i++){
			spittles.add(new Spittle("Spittle " + i, new Date(), new Spitter()));
		}
		return spittles;
	}
}
