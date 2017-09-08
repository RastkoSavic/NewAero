package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.UserSettingsDAO;
import com.aerobase.app.entity.UserSettings;
import com.aerobase.app.service.UserSettingsService;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {

	@Autowired
	private UserSettingsDAO userSettingsDAO;
	
	@Override
	@Transactional
	public void save(UserSettings userSettings) {

		userSettingsDAO.save(userSettings);
	}

	@Override
	@Transactional
	public List<UserSettings> getAll() {

		return userSettingsDAO.getAll();
	}

	@Override
	@Transactional
	public UserSettings getByUserId(int userId) {

		return userSettingsDAO.getByUserId(userId);
	}

	@Override
	@Transactional
	public void update(UserSettings userSettings) {

		userSettingsDAO.update(userSettings);
	}

	@Override
	@Transactional
	public void delete(UserSettings userSettings) {

		userSettingsDAO.delete(userSettings);
	}

}
