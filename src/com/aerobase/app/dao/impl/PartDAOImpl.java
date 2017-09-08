package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.PartDAO;
import com.aerobase.app.entity.Part;

@Repository
public class PartDAOImpl implements PartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Part part) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(part);
	}

	@Override
	public List<Part> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Part> query = currentSession.createQuery("FROM Part", Part.class);

		List<Part> parts = query.getResultList();

		return parts;
	}

	@Override
	public Part getByPartNumber(String partNumber) {
		Session currentSession = sessionFactory.getCurrentSession();

		Part part = (Part) currentSession.get(Part.class, partNumber);
		
		return part;
	}

	@Override
	public void update(Part part) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(part);
	}

	@Override
	public void delete(Part part) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(part);
	}

}
