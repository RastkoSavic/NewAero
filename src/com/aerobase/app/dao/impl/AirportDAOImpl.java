package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.AirportDAO;
import com.aerobase.app.entity.Airport;

@Repository
public class AirportDAOImpl implements AirportDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Airport airport) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(airport);
	}

	@Override
	public List<Airport> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Airport> query = currentSession.createQuery("FROM Airport", Airport.class);

		List<Airport> airports = query.getResultList();

		for (Airport airport : airports) {
			
			Hibernate.initialize(airport.getAddress());
			Hibernate.initialize(airport.getAddress().getCity());
		}
		
		return airports;
	}

	@Override
	public Airport getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Airport airport = (Airport) currentSession.get(Airport.class, id);

		Hibernate.initialize(airport);
		
		return airport;
	}

	@Override
	public void update(Airport airport) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(airport);
	}

	@Override
	public void delete(Airport airport) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(airport);
	}

}
