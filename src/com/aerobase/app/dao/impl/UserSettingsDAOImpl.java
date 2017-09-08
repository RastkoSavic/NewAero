package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.UserSettingsDAO;
import com.aerobase.app.entity.UserSettings;

@Repository
public class UserSettingsDAOImpl implements UserSettingsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserSettings userSettings) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(userSettings);
	}

	@Override
	public List<UserSettings> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<UserSettings> query = currentSession.createQuery("FROM UserSettings", UserSettings.class);

		List<UserSettings> userSettingsList = query.getResultList();

		return userSettingsList;
	}

	@Override
	public UserSettings getByUserId(int userId) {
		Session currentSession = sessionFactory.getCurrentSession();

		UserSettings userSettings = (UserSettings) currentSession.load(UserSettings.class, userId);

		return userSettings;
	}

	@Override
	public void update(UserSettings userSettings) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(userSettings);
	}

	@Override
	public void delete(UserSettings userSettings) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(userSettings);
	}

}
