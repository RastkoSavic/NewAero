package com.aerobase.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.CityDAO;
import com.aerobase.app.dao.CountryDAO;
import com.aerobase.app.entity.City;
import com.aerobase.app.entity.Country;
import com.aerobase.app.model.CityModel;
import com.aerobase.app.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDAO cityDAO;

	@Autowired
	private CountryDAO countryDAO;

	@Override
	@Transactional
	public void save(City city) {

		cityDAO.save(city);
	}

	@Override
	@Transactional
	public List<City> getAll() {

		return cityDAO.getAll();
	}

	@Override
	@Transactional
	public City getById(int id) {

		return cityDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(City city) {

		cityDAO.update(city);
	}

	@Override
	@Transactional
	public boolean delete(City city) {

		City c = cityDAO.getById(city.getId());

		if (c.getAddresses().size() > 0) {

			return false;
		} else {
			cityDAO.delete(c);

			return true;
		}

	}

	@Override
	@Transactional
	public List<CityModel> listForModel(String countryCode) {

		List<CityModel> cityModels = new ArrayList<>();

		Country country = countryDAO.getByCode(countryCode);
		Set<City> cities = country.getCities();

		for (City city : cities) {
			cityModels.add(new CityModel(city.getId(), city.getName()));
		}

		return cityModels;
	}

}
