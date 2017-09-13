package com.vt.spring.repository;

import java.util.List;

import com.vt.spring.domain.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);
}
