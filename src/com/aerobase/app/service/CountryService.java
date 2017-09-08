package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Country;

public interface CountryService {

	public void save(Country country);

	public List<Country> getAll();

	public Country getByCode(String code);

	public void update(Country country);

	public boolean delete(Country country);
}
