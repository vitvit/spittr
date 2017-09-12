package repository;

import java.util.List;

import domain.Spittle;

public interface SpittleRepository {
	List<Spittle> findSpittles(long max, int count);
}
