package com.vt.test.repository;

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
import com.vt.spring.config.WebConfig;
import com.vt.spring.domain.Spitter;
import com.vt.spring.repository.SpitterRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, DataSourceConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class JdbcSpitterRepositoryTest {
	
	@Autowired
	private SpitterRepository spitterRepository;
	
	@Test
	@Rollback
	public void saveTest() throws Exception {
		Spitter spitterToSave = new Spitter("username", "pass", "firstname", "lastname");
		Spitter savedSpitter = spitterRepository.save(spitterToSave);
		assertThat(savedSpitter, notNullValue());
		assertThat(savedSpitter.getId(), notNullValue());
		assertThat(savedSpitter.getUsername(), is("username"));
		assertThat(savedSpitter.getPassword(), is("pass"));
		assertThat(savedSpitter.getFirstname(), is("firstname"));
		assertThat(savedSpitter.getLastname(), is("lastname"));
	}
	
	@Test
	@Rollback
	public void findByUsernameTest() throws Exception {
		Spitter expectedSpitter = new Spitter("username", "pass", "firstname", "lastname");
		spitterRepository.save(expectedSpitter);
		Spitter fetchedSpitter = spitterRepository.findByUsername("username");
		assertThat(fetchedSpitter, notNullValue());
		assertThat(fetchedSpitter.getId(), notNullValue());
		assertThat(fetchedSpitter.getUsername(), is(expectedSpitter.getUsername()));
		assertThat(fetchedSpitter.getPassword(), is(expectedSpitter.getPassword()));
		assertThat(fetchedSpitter.getFirstname(), is(expectedSpitter.getFirstname()));
		assertThat(fetchedSpitter.getLastname(), is(expectedSpitter.getLastname()));
	}
}
