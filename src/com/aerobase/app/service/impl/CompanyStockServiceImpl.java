package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.CompanyStockDAO;
import com.aerobase.app.entity.CompanyStock;
import com.aerobase.app.service.CompanyStockService;

@Service
public class CompanyStockServiceImpl implements CompanyStockService {

	@Autowired
	private CompanyStockDAO companyStockDAO;

	@Override
	@Transactional
	public void save(CompanyStock companyStock) {

		companyStockDAO.save(companyStock);
	}

	@Override
	@Transactional
	public List<CompanyStock> getAll() {

		return companyStockDAO.getAll();
	}

	@Override
	@Transactional
	public CompanyStock getByCompanyId(int companyId) {

		return companyStockDAO.getByCompanyId(companyId);
	}

	@Override
	@Transactional
	public void update(CompanyStock companyStock) {

		companyStockDAO.update(companyStock);
	}

	@Override
	@Transactional
	public void delete(CompanyStock companyStock) {

		companyStockDAO.delete(companyStock);
	}

}
