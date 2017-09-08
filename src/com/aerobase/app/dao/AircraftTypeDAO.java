package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.AircraftType;

public interface AircraftTypeDAO {

	public void save(AircraftType aircraftType);

	public List<AircraftType> getAll();

	public AircraftType getById(int id);

	public void update(AircraftType aircraftType);

	public void delete(AircraftType aircraftType);
}
