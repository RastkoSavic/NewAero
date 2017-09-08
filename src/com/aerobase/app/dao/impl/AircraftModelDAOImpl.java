package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.AircraftModelDAO;
import com.aerobase.app.entity.AircraftModel;

@Repository
public class AircraftModelDAOImpl implements AircraftModelDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(AircraftModel aircraftModel) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(aircraftModel);
	}

	@Override
	public List<AircraftModel> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AircraftModel> query = currentSession.createQuery("FROM AircraftModel", AircraftModel.class);

		List<AircraftModel> aircraftModels = query.getResultList();
		
		return aircraftModels;
	}

	@Override
	public AircraftModel getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		AircraftModel aircraftModel = (AircraftModel) currentSession.load(AircraftModel.class, id);

		return aircraftModel;
	}

	@Override
	public void update(AircraftModel aircraftModel) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(aircraftModel);
	}

	@Override
	public void delete(AircraftModel aircraftModel) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(aircraftModel);
	}

}
