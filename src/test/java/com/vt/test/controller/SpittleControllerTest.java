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
		
		when(spittleRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittleList);
		mock.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittleList.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (int i = 0; i < count; i++){
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
}
