package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Country;

public interface CountryDAO {

	public void save(Country country);

	public List<Country> getAll();

	public Country getByCode(String code);

	public void update(Country country);

	public void delete(Country country);
}
