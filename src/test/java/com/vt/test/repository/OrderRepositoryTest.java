package com.vt.test.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vt.spring.config.MongoConfig;
import com.vt.spring.document.Item;
import com.vt.spring.document.Order;
import com.vt.spring.mongo.repository.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=MongoConfig.class)
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;
	
	@Before
	public void cleanup() {
		orderRepository.deleteAll();
	}
	
	@Test
	public void findChucksOrder() throws Exception {
		Collection<Item> items = new LinkedHashSet<>();
		items.add(new Item());
		Order orderToSave = new Order("chuck", "type", items);
		Order expectedOrder = orderRepository.save(orderToSave);
		
		List<Order> orders = orderRepository.findByCustomerAndType(expectedOrder.getCustomer(), expectedOrder.getType());
		
		assertThat(orders, notNullValue());
		assertThat(orders, hasSize(1));
		assertThat(orders.get(0).getCustomer(), is(expectedOrder.getCustomer()));
		assertThat(orders.get(0).getType(), is(expectedOrder.getType()));
		assertThat(orders.get(0).getItems(), hasSize(1));
	}
}
