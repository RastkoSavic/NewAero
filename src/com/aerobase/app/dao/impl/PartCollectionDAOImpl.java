package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.PartCollectionDAO;
import com.aerobase.app.entity.PartCollection;

@Repository
public class PartCollectionDAOImpl implements PartCollectionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(PartCollection partCollection) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(partCollection);
	}

	@Override
	public List<PartCollection> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<PartCollection> query = currentSession.createQuery("FROM PartCollection", PartCollection.class);

		List<PartCollection> partCollections = query.getResultList();

		return partCollections;
	}

	@Override
	public PartCollection getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		PartCollection partCollection = (PartCollection) currentSession.get(PartCollection.class, id);

		return partCollection;
	}

	@Override
	public void update(PartCollection partCollection) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(partCollection);
	}

	@Override
	public void delete(PartCollection partCollection) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(partCollection);
	}

}
