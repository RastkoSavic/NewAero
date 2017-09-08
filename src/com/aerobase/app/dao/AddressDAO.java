package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Address;

public interface AddressDAO {

	public void save(Address address);

	public List<Address> getAll();

	public Address getById(int id);

	public void update(Address address);

	public void delete(Address address);
}
