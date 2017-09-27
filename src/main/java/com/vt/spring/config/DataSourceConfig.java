package com.vt.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class DataSourceConfig {

	@Profile("dev")
	@Bean
	public DataSource embeddedDataSource() {
		return new EmbeddedDatabaseBuilder()
					.setType(EmbeddedDatabaseType.H2)
					.addScript("db/schema.sql")
					.build();
	}
	
	/*@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
	  LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
	  sfb.setDataSource(dataSource);
	  sfb.setPackagesToScan(new String[] { "com.habuma.spittr.domain" });
	  Properties props = new Properties();
	  props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
	  sfb.setHibernateProperties(props);
	  return sfb;
	}
*/
	
	@Profile("qa")
	@Bean
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost/spittr");
		basicDataSource.setUsername("root");
		basicDataSource.setPassword("devcom");
		basicDataSource.setInitialSize(5);
		basicDataSource.setMaxActive(10);
		return basicDataSource;
	}
	
	@Profile("prod")
	@Bean
	public DataSource dataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/spittrDS");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
		return (DataSource) jndiObjectFactoryBean.getObject();
	}
	
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
      return new JdbcTemplate(dataSource);
    }
}
