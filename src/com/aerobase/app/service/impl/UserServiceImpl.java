package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.UserDAO;
import com.aerobase.app.dao.UserSettingsDAO;
import com.aerobase.app.entity.User;
import com.aerobase.app.entity.UserSettings;
import com.aerobase.app.model.LoginModel;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.model.UserSettingsModel;
import com.aerobase.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserSettingsDAO userSettingsDAO;

	@Override
	@Transactional
	public int save(User user) {

		int id = userDAO.save(user);

		return id;
	}

	@Override
	@Transactional
	public List<User> getAll() {

		return userDAO.getAll();
	}

	@Override
	@Transactional
	public User getById(int id) {

		return userDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(User user) {

		userDAO.update(user);
	}

	@Override
	@Transactional
	public boolean delete(User user) {

		User u = userDAO.getById(user.getId());

		if (u.getMessages().size() > 0 || u.getRecipientIN().size() > 0 || u.getStockParts().size() > 0) {

			return false;
		} else {
			UserSettings us = userSettingsDAO.getByUserId(u.getId());
			userSettingsDAO.delete(us);
			userDAO.delete(u);

			return true;
		}

	}

	@Override
	@Transactional
	public UserModel login(LoginModel loginModel) {

		if (loginModel.getEmail() != null && loginModel.getPassword() != null) {

			User user = userDAO.getByEmail(loginModel.getEmail());

			if (user != null && user.getPassword().equals(loginModel.getPassword())) {

				return new UserModel(user.getId(), user.getStatus().getStatus(), user.getCompany().getId());
			} else {

				return null;
			}
		} else {

			return null;
		}
	}

	@Override
	@Transactional
	public UserSettingsModel forSettingsModel(int userId) {

		User user = userDAO.getById(userId);
		UserSettings userSettings = userSettingsDAO.getByUserId(userId);

		UserSettingsModel settingsModel = new UserSettingsModel(user.getId(), user.getFirstName(), user.getLastName(),
				user.getPosition(), user.getPhone(), user.getPhoneAlt(), user.getEmail(), user.getEmailAlt(),
				userSettings.getNotes());

		settingsModel.setEmailAllowed((userSettings.getEmail() == 1) ? true : false);
		settingsModel.setPhoneAllowed((userSettings.getPhone() == 1) ? true : false);
		settingsModel.setMessageAllowed((userSettings.getMessage() == 1) ? true : false);

		return settingsModel;
	}

	@Override
	@Transactional
	public void setAll(UserSettingsModel settingsModel, Integer usId) {

		User user = userDAO.getById(usId);
		UserSettings userSettings = userSettingsDAO.getByUserId(usId);

		user.setFirstName(settingsModel.getFirstName());
		user.setLastName(settingsModel.getLastName());
		user.setPosition(settingsModel.getPosition());
		user.setPhone(settingsModel.getPhone());
		user.setPhoneAlt(settingsModel.getPhoneAlt());
		user.setEmail(settingsModel.getEmail());
		user.setEmailAlt(settingsModel.getEmailAlt());

		userSettings.setNotes(settingsModel.getNotes());

		userSettings.setPhone(settingsModel.isPhoneAllowed() ? 1 : 0);
		userSettings.setEmail(settingsModel.isEmailAllowed() ? 1 : 0);
		userSettings.setMessage(settingsModel.isMessageAllowed() ? 1 : 0);

		userDAO.update(user);
		userSettingsDAO.update(userSettings);
	}

	@Override
	public User getByEmail(String email) {
		User user = userDAO.getByEmail(email);

		return user;
	}

}
