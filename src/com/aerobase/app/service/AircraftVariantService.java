package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.AircraftVariant;
import com.aerobase.app.model.AircraftVariantModel;

public interface AircraftVariantService {

	public void save(AircraftVariant aircraftVariant);

	public List<AircraftVariant> getAll();

	public AircraftVariant getById(int id);

	public void update(AircraftVariant aircraftVariant);

	public boolean delete(AircraftVariant aircraftVariant);

	public List<AircraftVariantModel> listForModel(Integer object);
}
