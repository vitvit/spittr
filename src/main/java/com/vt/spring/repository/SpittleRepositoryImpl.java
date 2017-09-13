package com.vt.spring.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vt.spring.domain.Spittle;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {

	@Override
	public List<Spittle> findSpittles(long max, int count) {	
		return null;
	}
}
