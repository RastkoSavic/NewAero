package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.CompanyStockDAO;
import com.aerobase.app.entity.CompanyStock;

@Repository
public class CompanyStockDAOImpl implements CompanyStockDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(CompanyStock stock) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(stock);
	}

	@Override
	public List<CompanyStock> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<CompanyStock> query = currentSession.createQuery("FROM CompanyStock", CompanyStock.class);

		List<CompanyStock> stocks = query.getResultList();

		return stocks;
	}

	@Override
	public CompanyStock getByCompanyId(int companyId) {
		Session currentSession = sessionFactory.getCurrentSession();

		CompanyStock stock = (CompanyStock) currentSession.load(CompanyStock.class, companyId);

		Hibernate.initialize(stock.getStockParts());
		
		return stock;
	}

	@Override
	public void update(CompanyStock stock) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(stock);
	}

	@Override
	public void delete(CompanyStock stock) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(stock);
	}

}
