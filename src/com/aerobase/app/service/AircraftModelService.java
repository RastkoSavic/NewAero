package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.AircraftModel;
import com.aerobase.app.model.AircraftModelModel;

public interface AircraftModelService {

	public void save(AircraftModel aircraftModel);

	public List<AircraftModel> getAll();

	public AircraftModel getById(int id);

	public void update(AircraftModel aircraftModel);

	public boolean delete(AircraftModel aircraftModel);
	
	public List<AircraftModelModel> listForModel(int typeId);
}
