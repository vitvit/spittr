package com.vt.spring.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vt.spring.domain.Spittle;

@Repository
@Transactional
public class HibernateSpittleRepository implements SpittleRepository {

	private final SessionFactory sessionFactory;
	
	@Autowired
	public HibernateSpittleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Spittle save(Spittle spittle) {
		Serializable id = getCurrentSession().save(spittle);
		return new Spittle((Long) id,
							spittle.getMessage(),
							spittle.getPostedTime());
	}

	@Override
	public Spittle findOne(long id) {
		return (Spittle) getCurrentSession().get(Spittle.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Spittle> findRecentSpittles(int count) {
		return (List<Spittle>) getCurrentSession().createCriteria(Spittle.class)
								.addOrder(Order.desc("postedTime"))
								.setMaxResults(count)
								.list();
	}
}
