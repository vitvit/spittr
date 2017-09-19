package com.vt.spring.config;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.vt.spring.repository.SpitterRepository;

import security.SpitterUserService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String DEF_USER_BY_USERNAME_QUERY = 
			"SELECT username, password, enabled " +
			"FROM user " +
			"WHERE username = ?";
	public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
	        "select username,authority " +
	        "from authority " +
	        "where username = ?";
	public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
	        "select g.id, g.group_name, ga.authority " +
	        "from group g, group_member gm, group_authority ga " +
	        "where gm.username = ? " +
	        "and g.id = ga.group_id " +
	        "and g.id = gm.group_id";
	
	//@Autowired
	//DataSource dataSource;
	@Autowired
	private SpitterRepository spitterRepository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//in memory auth
		/*
		auth.inMemoryAuthentication()
			.withUser("user").password("password").authorities("ROLE_USER").and()
			.withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
		*/
		//datasource auth
		/*
		auth.jdbcAuthentication().dataSource(dataSource)
			.authoritiesByUsernameQuery("SELECT username, password, true " +
										"FROM Spitter " +
										"WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT username, 'ROLE_USER' " +
										"FROM Spitter where username=?")
			.passwordEncoder(new BCryptPasswordEncoder());
		*/
		//LDAP auth
		/*
		auth.ldapAuthentication().userSearchBase("ou=peaople")
								 .userSearchFilter("(uid={0})")
								 .groupSearchBase("groups")
								 .groupSearchFilter("group={0}")
								 //.passwordCompare()
								 //.passwordEncoder(new Md5PasswordEncoder())
								 //.passwordAttribute("passcode")
								 .contextSource()
								 	.url("ldap://testldap.com:389/dc=testldap,dc=com");
		*/
		//custom user service
		auth.userDetailsService(new SpitterUserService(spitterRepository));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.loginPage("/login")
			  .and()
			.rememberMe()
		        .tokenValiditySeconds(2419200)
		        .key("spittrKey")
		      .and()
		    .logout()
		    	.logoutUrl("/logout")
		        .logoutSuccessUrl("/")
		        .invalidateHttpSession(true)
		      .and()
			.authorizeRequests()
				.antMatchers("/spitters/vitt").hasRole("SPITTER")
				.antMatchers("/spittles").authenticated()
				.antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
				.anyRequest().permitAll()
			  .and()
			.requiresChannel()
				.antMatchers("/spitter/form").requiresSecure();
	}
}
