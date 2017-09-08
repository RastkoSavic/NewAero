package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Airport;

public interface AirportDAO {

	public void save(Airport airport);

	public List<Airport> getAll();

	public Airport getById(int id);

	public void update(Airport airport);

	public void delete(Airport airport);
}
