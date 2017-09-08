package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.PartCategoryDAO;
import com.aerobase.app.entity.PartCategory;

@Repository
public class PartCategoryDAOImpl implements PartCategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(PartCategory category) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(category);
	}

	@Override
	public List<PartCategory> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<PartCategory> query = currentSession.createQuery("FROM PartCategory", PartCategory.class);

		List<PartCategory> categories = query.getResultList();

		return categories;
	}

	@Override
	public PartCategory getByCategory(String name) {
		Session currentSession = sessionFactory.getCurrentSession();

		PartCategory category = (PartCategory) currentSession.load(PartCategory.class, name);
		
		return category;
	}

	@Override
	public void update(PartCategory category) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(category);
	}

	@Override
	public void delete(PartCategory category) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(category);
	}

}
