package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.AircraftType;

public interface AircraftTypeService {

	public void save(AircraftType aircraftType);

	public List<AircraftType> getAll();

	public AircraftType getById(int id);

	public void update(AircraftType aircraftType);

	public boolean delete(AircraftType aircraftType);
}
