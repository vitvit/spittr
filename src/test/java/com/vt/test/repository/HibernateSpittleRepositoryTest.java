package com.vt.test.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vt.spring.config.DataSourceConfig;
import com.vt.spring.config.PersistanceConfig;
import com.vt.spring.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, DataSourceConfig.class, PersistanceConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class HibernateSpittleRepositoryTest {
	
	@Test
	public void findRecentSpittlesTest() throws Exception {
		
	}
	
	@Test
	public void findOneTest() throws Exception {
		
	}
	
	@Test
	public void saveTest() throws Exception {

	}
}
