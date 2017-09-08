package com.aerobase.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AirportDAO;
import com.aerobase.app.dao.CityDAO;
import com.aerobase.app.entity.Address;
import com.aerobase.app.entity.Airport;
import com.aerobase.app.entity.City;
import com.aerobase.app.model.AirportModel;
import com.aerobase.app.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportDAO airportDAO;

	@Autowired
	private CityDAO cityDAO;

	@Override
	@Transactional
	public void save(Airport airport) {

		airportDAO.save(airport);
	}

	@Override
	@Transactional
	public List<Airport> getAll() {

		return airportDAO.getAll();
	}

	@Override
	@Transactional
	public Airport getById(int id) {

		return airportDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(Airport airport) {

		airportDAO.update(airport);
	}

	@Override
	@Transactional
	public boolean delete(Airport airport) {

		Airport a = airportDAO.getById(airport.getId());

		if (a.getAOGSet().size() > 0) {

			return false;
		} else {
			airportDAO.delete(a);

			return true;
		}

	}

	@Override
	@Transactional
	public List<AirportModel> listForModel(Integer object) {
		List<AirportModel> airportModels = new ArrayList<>();

		City city = cityDAO.getById(object);
		Set<Address> addresses = city.getAddresses();

		for (Address address : addresses) {
			if (address.getAirport() != null) {
				AirportModel airportModel = new AirportModel(address.getAirport().getId(),
						address.getAirport().getName(), address.getAirport().getIATACode(), address.getAddress());
				airportModels.add(airportModel);
			}
		}

		return airportModels;
	}

}
