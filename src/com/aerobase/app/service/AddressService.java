package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Address;

public interface AddressService {

	public void save(Address address);

	public List<Address> getAll();

	public Address getById(int id);

	public void update(Address address);

	public boolean delete(Address address);
}
