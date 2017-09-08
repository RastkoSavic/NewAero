package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.AircraftVariantDAO;
import com.aerobase.app.entity.AircraftVariant;

@Repository
public class AircraftVariantDAOImpl implements AircraftVariantDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(AircraftVariant aircraftVariant) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(aircraftVariant);
	}

	@Override
	public List<AircraftVariant> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AircraftVariant> query = currentSession.createQuery("FROM AircraftVariant", AircraftVariant.class);

		List<AircraftVariant> aircraftVariants = query.getResultList();
		
		for (AircraftVariant aircraftVariant : aircraftVariants) {
			
			Hibernate.initialize(aircraftVariant.getModel());
		}

		return aircraftVariants;
	}

	@Override
	public AircraftVariant getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		AircraftVariant aircraftVariant = (AircraftVariant) currentSession.load(AircraftVariant.class, id);

		return aircraftVariant;
	}

	@Override
	public void update(AircraftVariant aircraftVariant) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(aircraftVariant);
	}

	@Override
	public void delete(AircraftVariant aircraftVariant) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(aircraftVariant);
	}

}
