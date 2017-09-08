package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.CountryDAO;
import com.aerobase.app.entity.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Country country) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(country);

	}

	@Override
	public List<Country> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Country> query = currentSession.createQuery("FROM Country", Country.class);

		List<Country> countries = query.getResultList();

		return countries;
	}

	@Override
	public Country getByCode(String code) {
		Session currentSession = sessionFactory.getCurrentSession();

		Country country = (Country) currentSession.load(Country.class, code);

		return country;
	}

	@Override
	public void update(Country country) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(country);

	}

	@Override
	public void delete(Country country) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(country);

	}

}
