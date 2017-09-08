package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.UserStatus;

public interface UserStatusService {

	public void save(UserStatus userStatus);

	public List<UserStatus> getAll();

	public UserStatus getByStatus(String status);

	public void update(UserStatus userStatus);

	public void delete(UserStatus userStatus);
}
