package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.CompanyStock;

public interface CompanyStockService {

	public void save(CompanyStock companyStock);

	public List<CompanyStock> getAll();

	public CompanyStock getByCompanyId(int companyId);

	public void update(CompanyStock companyStock);

	public void delete(CompanyStock companyStock);
}
