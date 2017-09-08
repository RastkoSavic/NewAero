package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.PrimaryServiceDAO;
import com.aerobase.app.entity.PrimaryService;
import com.aerobase.app.service.PrimaryServiceService;

@Service
public class PrimaryServiceServiceImpl implements PrimaryServiceService {

	@Autowired
	private PrimaryServiceDAO primaryServiceDAO;

	@Override
	@Transactional
	public void save(PrimaryService primaryService) {

		primaryServiceDAO.save(primaryService);
	}

	@Override
	@Transactional
	public List<PrimaryService> getAll() {

		return primaryServiceDAO.getAll();
	}

	@Override
	@Transactional
	public PrimaryService getById(int id) {

		return primaryServiceDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(PrimaryService primaryService) {

		primaryServiceDAO.update(primaryService);
	}

	@Override
	@Transactional
	public boolean delete(PrimaryService primaryService) {
		System.out.println("pocetak deleta");
		PrimaryService ps = primaryServiceDAO.getById(primaryService.getId());

		if (ps.getCompanies().size() > 0) {

			return false;
		} else {
			primaryServiceDAO.delete(ps);

			return true;
		}

	}

}
