package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.PrimaryServiceDAO;
import com.aerobase.app.entity.PrimaryService;

@Repository
public class PrimaryServiceDAOImpl implements PrimaryServiceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(PrimaryService primaryService) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(primaryService);
	}

	@Override
	public List<PrimaryService> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<PrimaryService> query = currentSession.createQuery("FROM PrimaryService", PrimaryService.class);

		List<PrimaryService> primaryServices = query.getResultList();

		return primaryServices;
	}

	@Override
	public PrimaryService getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		PrimaryService primaryService = (PrimaryService) currentSession.load(PrimaryService.class, id);

		return primaryService;
	}

	@Override
	public void update(PrimaryService primaryService) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(primaryService);
	}

	@Override
	public void delete(PrimaryService primaryService) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(primaryService);
	}

}
