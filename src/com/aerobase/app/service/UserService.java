package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.User;
import com.aerobase.app.model.LoginModel;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.model.UserSettingsModel;

public interface UserService {

	public int save(User user);

	public List<User> getAll();

	public User getById(int id);

	public void update(User user);

	public boolean delete(User user);

	public UserModel login(LoginModel loginModel);

	public UserSettingsModel forSettingsModel(int userId);

	public void setAll(UserSettingsModel settingsModel, Integer usId);

	public User getByEmail(String email);

}
