package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AddressDAO;
import com.aerobase.app.entity.Address;
import com.aerobase.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDAO addressDAO;

	@Override
	@Transactional
	public void save(Address address) {

		addressDAO.save(address);
	}

	@Override
	@Transactional
	public List<Address> getAll() {

		return addressDAO.getAll();
	}

	@Override
	@Transactional
	public Address getById(int id) {

		return addressDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(Address address) {

		addressDAO.update(address);
	}

	@Override
	@Transactional
	public boolean delete(Address address) {

		Address addressDel = addressDAO.getById(address.getId());
		if (addressDel.getAirport() != null || addressDel.getCompanies().size() > 0) {

			return false;
		} else {
			addressDAO.delete(addressDel);

			return true;
		}

	}

}
