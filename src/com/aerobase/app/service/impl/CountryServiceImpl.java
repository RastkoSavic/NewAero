package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.CountryDAO;
import com.aerobase.app.entity.Country;
import com.aerobase.app.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;

	@Override
	@Transactional
	public void save(Country country) {

		countryDAO.save(country);
	}

	@Override
	@Transactional
	public List<Country> getAll() {

		return countryDAO.getAll();
	}

	@Override
	@Transactional
	public Country getByCode(String code) {

		return countryDAO.getByCode(code);
	}

	@Override
	@Transactional
	public void update(Country country) {

		countryDAO.update(country);
	}

	@Override
	@Transactional
	public boolean delete(Country country) {
		Country c = countryDAO.getByCode(country.getCode());
		if (c.getCities().size() > 0) {

			return false;
		} else {
			countryDAO.delete(c);

			return true;
		}

	}

}
