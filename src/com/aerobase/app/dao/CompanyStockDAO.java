package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.CompanyStock;

public interface CompanyStockDAO {

	public void save(CompanyStock stock);

	public List<CompanyStock> getAll();

	public CompanyStock getByCompanyId(int companyId);

	public void update(CompanyStock stock);

	public void delete(CompanyStock stock);
}
