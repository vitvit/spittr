package com.vt.spring.repository;

import com.vt.spring.domain.Spitter;

public interface SpitterRepository {
	Spitter save(Spitter spitter);
	Spitter findByUsername(String username);
}
