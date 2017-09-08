package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.AircraftVariant;

public interface AircraftVariantDAO {

	public void save(AircraftVariant aircraftVariant);

	public List<AircraftVariant> getAll();

	public AircraftVariant getById(int id);

	public void update(AircraftVariant aircraftVariant);

	public void delete(AircraftVariant aircraftVariant);
}
