package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.CityDAO;
import com.aerobase.app.entity.City;

@Repository
public class CityDAOImpl implements CityDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(City city) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(city);
	}

	@Override
	public List<City> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<City> query = currentSession.createQuery("FROM City", City.class);

		List<City> cities = query.getResultList();

		return cities;
	}

	@Override
	public City getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		City city = (City) currentSession.get(City.class, id);
		
		Hibernate.initialize(city.getAddresses());

		return city;
	}

	@Override
	public void update(City city) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(city);
	}

	@Override
	public void delete(City city) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(city);
	}

}
