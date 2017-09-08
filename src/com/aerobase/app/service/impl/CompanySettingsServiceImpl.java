package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.CompanySettingsDAO;
import com.aerobase.app.entity.CompanySettings;
import com.aerobase.app.service.CompanySettingsService;

@Service
public class CompanySettingsServiceImpl implements CompanySettingsService {

	@Autowired
	private CompanySettingsDAO companySettingsDAO;
	
	@Override
	@Transactional
	public void save(CompanySettings companySettings) {

		companySettingsDAO.save(companySettings);
	}

	@Override
	@Transactional
	public List<CompanySettings> getAll() {

		return companySettingsDAO.getAll();
	}

	@Override
	@Transactional
	public CompanySettings getByCompanyId(int companyId) {

		return companySettingsDAO.getByCompanyId(companyId);
	}

	@Override
	@Transactional
	public void update(CompanySettings companySettings) {

		companySettingsDAO.update(companySettings);
	}

	@Override
	@Transactional
	public void delete(CompanySettings companySettings) {

		companySettingsDAO.delete(companySettings);
	}

}
