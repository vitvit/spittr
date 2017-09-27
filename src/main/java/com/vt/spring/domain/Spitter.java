package com.vt.spring.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "spitter")
public class Spitter {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Size(min=5, max=16, message="{username.size}")
	@Column(name = "username")
	private String username;
	
	@NotNull
	@Size(min=5, max=25,  message="{password.size}")
	@Column(name = "password")
	private String password;
	
	@NotNull
	@Size(min=2, max=30,  message="{firstname.size}")
	@Column(name = "firstname")
	private String firstname;
	
	@NotNull
	@Size(min=2, max=30,  message="{lastname.size}")
	@Column(name = "lastname")
	private String lastname;
	
	public Spitter() {}
	
	public Spitter(long id, String username, String password, String firstname, String lastname) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Spitter(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "id", "username");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "username");
	}
}
