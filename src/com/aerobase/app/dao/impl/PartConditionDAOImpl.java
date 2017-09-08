package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.PartConditionDAO;
import com.aerobase.app.entity.PartCondition;

@Repository
public class PartConditionDAOImpl implements PartConditionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(PartCondition condition) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(condition);
	}

	@Override
	public List<PartCondition> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<PartCondition> query = currentSession.createQuery("FROM PartCondition", PartCondition.class);

		List<PartCondition> conditions = query.getResultList();

		return conditions;
	}

	@Override
	public PartCondition getByCode(String code) {
		Session currentSession = sessionFactory.getCurrentSession();

		PartCondition condition = (PartCondition) currentSession.load(PartCondition.class, code);

		return condition;
	}

	@Override
	public void update(PartCondition condition) {
		Session currentSession = sessionFactory.getCurrentSession();
		/*
		 * @SuppressWarnings("rawtypes") Query query = currentSession
		 * .createQuery("update PartCondition as pc set name=:name, description=:description where code=:code"
		 * ); query.setParameter("name", condition.getName());
		 * query.setParameter("description", condition.getDescription());
		 * query.setParameter("code", condition.getCode()); query.executeUpdate();
		 */
		currentSession.update(condition);
	}

	@Override
	public void delete(PartCondition condition) {
		Session currentSession = sessionFactory.getCurrentSession();

		// Query query = currentSession.createQuery("delete PartCondition as pc where
		// code=:code");
		// query.setParameter("code", condition.getCode());
		// query.executeUpdate();
		
		currentSession.delete(condition);
	}

}
