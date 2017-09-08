package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.City;
import com.aerobase.app.model.CityModel;

public interface CityService {

	public void save(City city);

	public List<City> getAll();

	public City getById(int id);

	public void update(City city);

	public boolean delete(City city);
	
	public List<CityModel> listForModel(String countryCode);
}
