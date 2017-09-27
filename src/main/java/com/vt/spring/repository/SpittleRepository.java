package com.vt.spring.repository;

import java.util.List;

import com.vt.spring.domain.Spittle;

public interface SpittleRepository {
	List<Spittle> findRecentSpittles(int count);
	Spittle save(Spittle spittle);
	Spittle findOne(long id);
}
