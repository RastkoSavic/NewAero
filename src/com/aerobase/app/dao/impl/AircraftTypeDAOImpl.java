package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.AircraftTypeDAO;
import com.aerobase.app.entity.AircraftType;

@Repository
public class AircraftTypeDAOImpl implements AircraftTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(AircraftType aircraftType) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(aircraftType);
	}

	@Override
	public List<AircraftType> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AircraftType> query = currentSession.createQuery("FROM AircraftType", AircraftType.class);

		List<AircraftType> aircraftTypes = query.getResultList();

		return aircraftTypes;
	}

	@Override
	public AircraftType getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		AircraftType aircraftType = (AircraftType) currentSession.load(AircraftType.class, id);

		return aircraftType;
	}

	@Override
	public void update(AircraftType aircraftType) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(aircraftType);
	}

	@Override
	public void delete(AircraftType aircraftType) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(aircraftType);
	}

}
