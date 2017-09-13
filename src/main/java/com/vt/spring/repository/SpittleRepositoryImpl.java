package com.vt.spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vt.spring.domain.Spittle;

@Component
public class SpittleRepositoryImpl implements SpittleRepository {

	@Override
	public List<Spittle> findSpittles(long max, int count) {		 
		return createSpittleList(count);
	}
	
	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (long i = 0; i < count; i++){
			Spittle spittle = new Spittle("Spittle " + i, new Date());
			spittle.setId(i+1);
			spittles.add(spittle);
		}
		return spittles;
	}
}
