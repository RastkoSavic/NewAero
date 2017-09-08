package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.StockPartDAO;
import com.aerobase.app.entity.StockPart;

@Repository
public class StockPartDAOImpl implements StockPartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(StockPart stockPart) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(stockPart);
	}

	@Override
	public List<StockPart> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<StockPart> query = currentSession.createQuery("FROM StockPart", StockPart.class);

		List<StockPart> stockParts = query.getResultList();

		for (StockPart stockPart : stockParts) {

			Hibernate.initialize(stockPart.getPart());
			Hibernate.initialize(stockPart.getPart().getCategory());
			Hibernate.initialize(stockPart.getCondition());
			Hibernate.initialize(stockPart.getUser());
		}

		return stockParts;
	}

	@Override
	public StockPart getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		StockPart stockPart = (StockPart) currentSession.load(StockPart.class, id);

		return stockPart;
	}

	@Override
	public void update(StockPart stockPart) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(stockPart);
	}

	@Override
	public void delete(StockPart stockPart) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(stockPart);
	}

	@Override
	public List<StockPart> findByPart(String pNumber) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<StockPart> query = currentSession
				.createQuery("FROM StockPart as sp where sp.part.partNumber=" + "'" + pNumber + "'", StockPart.class);

		List<StockPart> stockParts = query.getResultList();

		for (StockPart stockPart : stockParts) {

			Hibernate.initialize(stockPart.getPart());
			Hibernate.initialize(stockPart.getPart().getCategory());
			Hibernate.initialize(stockPart.getCondition());
			Hibernate.initialize(stockPart.getStock());
			Hibernate.initialize(stockPart.getStock().getCompany());
		}

		return stockParts;
	}

	@Override
	public List<StockPart> findLatest() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<StockPart> query = currentSession.createQuery("FROM StockPart order by created", StockPart.class);

		query.setFirstResult(0);
		query.setMaxResults(15);

		List<StockPart> stockParts = query.getResultList();

		return stockParts;
	}

}
