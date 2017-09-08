package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Company;

public interface CompanyDAO {

	public void save(Company company);

	public List<Company> getAll();

	public Company getById(int id);

	public void update(Company company);

	public void delete(Company company);
}
