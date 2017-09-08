package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.UserSettings;

public interface UserSettingsDAO {

	public void save(UserSettings userSettings);

	public List<UserSettings> getAll();

	public UserSettings getByUserId(int userId);

	public void update(UserSettings userSettings);

	public void delete(UserSettings userSettings);
}
