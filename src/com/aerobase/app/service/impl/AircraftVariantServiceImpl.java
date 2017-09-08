package com.aerobase.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AircraftModelDAO;
import com.aerobase.app.dao.AircraftVariantDAO;
import com.aerobase.app.entity.AircraftModel;
import com.aerobase.app.entity.AircraftVariant;
import com.aerobase.app.model.AircraftVariantModel;
import com.aerobase.app.service.AircraftVariantService;

@Service
public class AircraftVariantServiceImpl implements AircraftVariantService {

	@Autowired
	private AircraftVariantDAO aircraftVariantDAO;

	@Autowired
	private AircraftModelDAO aircraftModelDAO;

	@Override
	@Transactional
	public void save(AircraftVariant aircraftVariant) {

		aircraftVariantDAO.save(aircraftVariant);
	}

	@Override
	@Transactional
	public List<AircraftVariant> getAll() {

		return aircraftVariantDAO.getAll();
	}

	@Override
	@Transactional
	public AircraftVariant getById(int id) {

		return aircraftVariantDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(AircraftVariant aircraftVariant) {

		aircraftVariantDAO.update(aircraftVariant);
	}

	@Override
	@Transactional
	public boolean delete(AircraftVariant aircraftVariant) {

		AircraftVariant av = aircraftVariantDAO.getById(aircraftVariant.getId());

		if (av.getAOGSet().size() > 0 || av.getParts().size() > 0) {

			return false;
		} else {
			aircraftVariantDAO.delete(av);

			return true;
		}

	}

	@Override
	@Transactional
	public List<AircraftVariantModel> listForModel(Integer object) {

		List<AircraftVariantModel> aircraftVariantModels = new ArrayList<>();

		AircraftModel aircraftModel = aircraftModelDAO.getById(object);
		Set<AircraftVariant> aircraftVariants = aircraftModel.getVariants();

		for (AircraftVariant aircraftVariant : aircraftVariants) {
			aircraftVariantModels.add(new AircraftVariantModel(aircraftVariant.getId(), aircraftVariant.getVariant()));
		}

		return aircraftVariantModels;
	}

}
