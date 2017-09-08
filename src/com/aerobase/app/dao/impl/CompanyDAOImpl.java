package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.CompanyDAO;
import com.aerobase.app.entity.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Company company) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(company);
	}

	@Override
	public List<Company> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Company> query = currentSession.createQuery("FROM Company", Company.class);

		List<Company> companies = query.getResultList();
		
		for (Company company : companies) {
			Hibernate.initialize(company.getPrimaryService());
			
		}

		return companies;
	}

	@Override
	public Company getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Company company = (Company) currentSession.load(Company.class, id);

		Hibernate.initialize(company);
		Hibernate.initialize(company.getUsers());
		Hibernate.initialize(company.getStock());
		Hibernate.initialize(company.getStock().getStockParts());
		Hibernate.initialize(company.getAddresses());
		
		return company;
	}

	@Override
	public void update(Company company) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(company);
	}

	@Override
	public void delete(Company company) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(company);
	}

}
