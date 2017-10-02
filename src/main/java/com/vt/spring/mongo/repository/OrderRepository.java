package com.vt.spring.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.vt.spring.document.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	
	@Query("{'customer':?0, 'type': ?1}")
	List<Order> findByCustomerAndType(String customer, String type);
}
