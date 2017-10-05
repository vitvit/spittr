package com.vt.test.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vt.spring.config.DataSourceConfig;
import com.vt.spring.config.PersistanceConfig;
import com.vt.spring.config.WebConfig;
import com.vt.spring.domain.Spitter;
import com.vt.spring.domain.Spittle;
import com.vt.spring.repository.SpitterRepository;
import com.vt.spring.repository.SpittleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, DataSourceConfig.class, PersistanceConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class HibernateSpittleRepositoryTest {

	@Autowired
	private SpitterRepository spitterRepository;
	@Autowired
	private SpittleRepository spittleRepository;
	
	@Test
	@Rollback
	public void saveTest() throws Exception {
		Spitter spitterToSave = new Spitter("username", "pass", "firstname", "lastname");
		Spitter savedSpitter = spitterRepository.save(spitterToSave);
		
		Spittle spittleToSave = new Spittle("message", new Date(), savedSpitter);
		Spittle savedSpittle = spittleRepository.save(spittleToSave);
		
		assertThat(savedSpittle, notNullValue());
		assertThat(savedSpittle.getId(), notNullValue());
		assertThat(savedSpittle.getMessage(), is("message"));
		assertThat(savedSpittle.getSpitter(), notNullValue());
		assertThat(savedSpittle.getSpitter().getUsername(), is("username"));
	}
	
	@Test
	public void findRecentSpittlesTest() throws Exception {
		int maxCount = 20;
		List<Spittle> expectedSpittles = createSpittleList(40);
		expectedSpittles.forEach(spittle -> spittleRepository.save(spittle));
		
		List<Spittle> spittles = spittleRepository.findRecentSpittles(maxCount);
		assertThat(spittles, notNullValue());
		assertThat(spittles, hasSize(maxCount));
	}
	
	@Test
	public void findOneTest() throws Exception {
		Spitter spitterToSave = new Spitter("username", "pass", "firstname", "lastname");
		Spitter savedSpitter = spitterRepository.save(spitterToSave);
		
		Spittle spittleToSave = new Spittle("message", new Date(), savedSpitter);
		Spittle savedSpittle = spittleRepository.save(spittleToSave);
		
		Spittle spittle = spittleRepository.findOne(savedSpittle.getId());
		
		assertThat(spittle, notNullValue());
		assertThat(spittle.getId(), notNullValue());
		assertThat(spittle.getMessage(), is(savedSpittle.getMessage()));
		assertThat(spittle.getSpitter(), notNullValue());
		assertThat(spittle.getSpitter().getUsername(), is(savedSpittle.getSpitter().getUsername()));
	}
	
	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (int i = 0; i < count; i++){
			
			spittles.add(new Spittle("Spittle " + i, new Date(), 
					spitterRepository.save(new Spitter("username", "pass", "firstname", "lastname"))));
		}
		return spittles;
	}
}
