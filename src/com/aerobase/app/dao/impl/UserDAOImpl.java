package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.UserDAO;
import com.aerobase.app.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(User user) {
		Session currentSession = sessionFactory.getCurrentSession();

		Integer id = (Integer) currentSession.save(user);

		return id;
	}

	@Override
	public List<User> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> query = currentSession.createQuery("FROM User", User.class);

		List<User> users = query.getResultList();

		for (User user : users) {

			Hibernate.initialize(user.getCompany());
			Hibernate.initialize(user.getStatus());
		}

		return users;
	}

	@Override
	public User getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		User user = (User) currentSession.load(User.class, id);

		Hibernate.initialize(user.getCompany());
		Hibernate.initialize(user.getCompany().getStock());
		Hibernate.initialize(user.getCompany().getStock().getStockParts());
		System.out.println(user.getId());
		return user;
	}

	@Override
	public void update(User user) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(user);
	}

	@Override
	public void delete(User user) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(user);
	}

	@Override
	public User getByEmail(String email) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> query = currentSession.createQuery("FROM User u where u.email=:email", User.class);

		query.setParameter("email", email);

		User user = query.uniqueResult();

		return user;
	}

}
