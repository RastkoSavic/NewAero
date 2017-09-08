package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.UserSettings;

public interface UserSettingsService {

	public void save(UserSettings userSettings);

	public List<UserSettings> getAll();

	public UserSettings getByUserId(int userId);

	public void update(UserSettings userSettings);

	public void delete(UserSettings userSettings);
}
