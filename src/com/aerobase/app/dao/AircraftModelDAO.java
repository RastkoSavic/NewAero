package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.AircraftModel;

public interface AircraftModelDAO {

	public void save(AircraftModel aircraftModel);

	public List<AircraftModel> getAll();

	public AircraftModel getById(int id);

	public void update(AircraftModel aircraftModel);

	public void delete(AircraftModel aircraftModel);
}
