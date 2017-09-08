package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.CompanySettings;

public interface CompanySettingsService {

	public void save(CompanySettings companySettings);

	public List<CompanySettings> getAll();

	public CompanySettings getByCompanyId(int companyId);

	public void update(CompanySettings companySettings);

	public void delete(CompanySettings companySettings);
}
