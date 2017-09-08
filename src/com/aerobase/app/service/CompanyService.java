package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Company;
import com.aerobase.app.model.CompanySettingsModel;

public interface CompanyService {

	public void save(Company company);

	public List<Company> getAll();

	public Company getById(int id);

	public void update(Company company);

	public void delete(Company company);
	
	public CompanySettingsModel forSettingsModel(int companyId);

	public void setAll(CompanySettingsModel companySettings, int companyId);
}
