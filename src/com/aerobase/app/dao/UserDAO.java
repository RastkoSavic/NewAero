package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.User;

public interface UserDAO {

	public int save(User user);

	public List<User> getAll();

	public User getById(int id);

	public void update(User user);

	public void delete(User user);
	
	public User getByEmail(String email);
}
