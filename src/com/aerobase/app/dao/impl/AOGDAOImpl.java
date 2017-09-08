package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.AOGDAO;
import com.aerobase.app.entity.AOG;

@Repository
public class AOGDAOImpl implements AOGDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(AOG aog) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(aog);
	}

	@Override
	public List<AOG> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<AOG> query = currentSession.createQuery("FROM AOG", AOG.class);

		List<AOG> aogs = query.getResultList();

		for (AOG aog : aogs) {
			Hibernate.initialize(aog.getAirport());
		}
		return aogs;
	}

	@Override
	public AOG getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		AOG aog = (AOG) currentSession.get(AOG.class, id);

		System.out.println(aog.getId());
		Hibernate.initialize(aog.getAirport());
		
		return aog;
	}

	@Override
	public void update(AOG aog) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(aog);
	}

	@Override
	public void delete(AOG aog) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(aog);
	}

}
