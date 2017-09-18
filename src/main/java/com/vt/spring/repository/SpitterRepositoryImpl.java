package com.vt.spring.repository;

import org.springframework.stereotype.Repository;

import com.vt.spring.domain.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

	@Override
	public Spitter save(Spitter spitter) {
		return spitter;
	}

	@Override
	public Spitter findByUsername(String username) {
		return new Spitter(333, username, "password", "firstname", "lastname");
	}
}
