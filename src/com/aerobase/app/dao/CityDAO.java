package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.City;

public interface CityDAO {

	public void save(City city);

	public List<City> getAll();

	public City getById(int id);

	public void update(City city);

	public void delete(City city);
}
