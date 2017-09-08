package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AircraftTypeDAO;
import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.service.AircraftTypeService;

@Service
public class AircraftTypeServiceImpl implements AircraftTypeService {

	@Autowired
	private AircraftTypeDAO aircraftTypeDAO;

	@Override
	@Transactional
	public void save(AircraftType aircraftType) {

		aircraftTypeDAO.save(aircraftType);
	}

	@Override
	@Transactional
	public List<AircraftType> getAll() {

		return aircraftTypeDAO.getAll();
	}

	@Override
	@Transactional
	public AircraftType getById(int id) {

		return aircraftTypeDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(AircraftType aircraftType) {

		aircraftTypeDAO.update(aircraftType);
	}

	@Override
	@Transactional
	public boolean delete(AircraftType aircraftType) {

		AircraftType at = aircraftTypeDAO.getById(aircraftType.getId());

		if (at.getModels().size() > 0) {

			return false;
		} else {
			aircraftTypeDAO.delete(at);

			return true;
		}

	}

}
