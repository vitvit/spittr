package com.vt.spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vt.spring.domain.Spittle;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

	private static final int SPITTLE_DEFAULT_COUNT = 20;
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {		 
		return createSpittleList(count);
	}
	
	@Override
	public Spittle findOne(long id) {
		return createSpittleList(SPITTLE_DEFAULT_COUNT).stream()
							 .filter(spittle -> ((Long)id).equals(spittle.getId()))
							 .findFirst().orElse(null);
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
