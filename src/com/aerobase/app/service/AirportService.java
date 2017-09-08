package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Airport;
import com.aerobase.app.model.AirportModel;

public interface AirportService {

	public void save(Airport airport);

	public List<Airport> getAll();

	public Airport getById(int id);

	public void update(Airport airport);

	public boolean delete(Airport airport);

	public List<AirportModel> listForModel(Integer object);
}
