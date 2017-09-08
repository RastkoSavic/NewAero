package com.aerobase.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AircraftModelDAO;
import com.aerobase.app.dao.AircraftTypeDAO;
import com.aerobase.app.entity.AircraftModel;
import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.model.AircraftModelModel;
import com.aerobase.app.service.AircraftModelService;

@Service
public class AircraftModelServiceImpl implements AircraftModelService {

	@Autowired
	private AircraftModelDAO aircraftModelDAO;

	@Autowired
	private AircraftTypeDAO aircraftTypeDAO;

	@Override
	@Transactional
	public void save(AircraftModel aircraftModel) {

		aircraftModelDAO.save(aircraftModel);
	}

	@Override
	@Transactional
	public List<AircraftModel> getAll() {

		return aircraftModelDAO.getAll();
	}

	@Override
	@Transactional
	public AircraftModel getById(int id) {

		return aircraftModelDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(AircraftModel aircraftModel) {

		aircraftModelDAO.update(aircraftModel);
	}

	@Override
	@Transactional
	public boolean delete(AircraftModel aircraftModel) {

		AircraftModel am = aircraftModelDAO.getById(aircraftModel.getId());

		if (am.getVariants().size() > 0) {

			return false;
		} else {
			aircraftModelDAO.delete(am);

			return true;
		}

	}

	@Override
	@Transactional
	public List<AircraftModelModel> listForModel(int typeId) {

		List<AircraftModelModel> aiModels = new ArrayList<>();

		AircraftType aircraftType = aircraftTypeDAO.getById(typeId);
		Set<AircraftModel> aiForType = aircraftType.getModels();

		for (AircraftModel aircraftModel : aiForType) {
			aiModels.add(new AircraftModelModel(aircraftModel.getId(), aircraftModel.getModel()));
		}

		return aiModels;
	}

}
