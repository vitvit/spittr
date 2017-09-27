package com.vt.spring.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "spittle")
public class Spittle {
	@Id
    @GeneratedValue
	private Long id;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "postedTime")
	private Date postedTime;

	@ManyToOne
	@JoinColumn(name = "spitter")
	private Spitter spitter;
	
	public Spittle(Long id, String message, Date postedTime) {
		this.id = id;
		this.message = message;
		this.postedTime = postedTime;
	}

	public Spittle(String message, Date postedTime) {
		this(null, message, postedTime);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "id", "postedTime");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "postedTime");
	}
}
