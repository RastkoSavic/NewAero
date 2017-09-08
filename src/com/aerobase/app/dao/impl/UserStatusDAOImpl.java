package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.UserStatusDAO;
import com.aerobase.app.entity.UserStatus;

@Repository
public class UserStatusDAOImpl implements UserStatusDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserStatus userStatus) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(userStatus);
	}

	@Override
	public List<UserStatus> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<UserStatus> query = currentSession.createQuery("FROM UserStatus", UserStatus.class);

		List<UserStatus> userStatuses = query.getResultList();

		return userStatuses;
	}

	@Override
	public UserStatus getByStatus(String status) {
		Session currentSession = sessionFactory.getCurrentSession();

		UserStatus userStatus = (UserStatus) currentSession.load(UserStatus.class, status);

		return userStatus;
	}

	@Override
	public void update(UserStatus userStatus) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(userStatus);
	}

	@Override
	public void delete(UserStatus userStatus) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(userStatus);
	}

}
