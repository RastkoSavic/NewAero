package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.UserStatusDAO;
import com.aerobase.app.entity.UserStatus;
import com.aerobase.app.service.UserStatusService;

@Service
public class UserStatusServiceImpl implements UserStatusService {

	@Autowired
	private UserStatusDAO userStatusDAO;

	@Override
	@Transactional
	public void save(UserStatus userStatus) {

		userStatusDAO.save(userStatus);
	}

	@Override
	@Transactional
	public List<UserStatus> getAll() {

		return userStatusDAO.getAll();
	}

	@Override
	@Transactional
	public UserStatus getByStatus(String status) {

		return userStatusDAO.getByStatus(status);
	}

	@Override
	@Transactional
	public void update(UserStatus userStatus) {

		userStatusDAO.update(userStatus);
	}

	@Override
	@Transactional
	public void delete(UserStatus userStatus) {

		userStatusDAO.delete(userStatus);
	}

}
