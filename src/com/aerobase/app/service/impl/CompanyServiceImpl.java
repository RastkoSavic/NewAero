package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.CompanyDAO;
import com.aerobase.app.dao.CompanySettingsDAO;
import com.aerobase.app.entity.Company;
import com.aerobase.app.entity.CompanySettings;
import com.aerobase.app.model.CompanySettingsModel;
import com.aerobase.app.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private CompanySettingsDAO companySettingsDAO;

	@Override
	@Transactional
	public void save(Company company) {

		companyDAO.save(company);
	}

	@Override
	@Transactional
	public List<Company> getAll() {

		return companyDAO.getAll();
	}

	@Override
	@Transactional
	public Company getById(int id) {

		return companyDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(Company company) {

		companyDAO.update(company);
	}

	@Override
	@Transactional
	public void delete(Company company) {

		companyDAO.delete(company);
	}

	@Override
	@Transactional
	public CompanySettingsModel forSettingsModel(int companyId) {

		Company company = companyDAO.getById(companyId);

		CompanySettings companySettings = companySettingsDAO.getByCompanyId(companyId);

		CompanySettingsModel companySettingsModel = new CompanySettingsModel();

		companySettingsModel.setLogoUrl(company.getLogoURL());
		companySettingsModel.setPhone(company.getPhone());
		companySettingsModel.setPhoneAlt(company.getPhoneAlt());
		companySettingsModel.setEmail(company.getEmail());
		companySettingsModel.setEmailAlt(company.getEmailAlt());
		companySettingsModel.setWeb(company.getWeb());

		companySettingsModel.setNotes(companySettings.getNotes());

		companySettingsModel.setLogo((companySettings.getLogo() == 1) ? true : false);
		companySettingsModel.setPhoneAllowed((companySettings.getPhone() == 1) ? true : false);
		companySettingsModel.setEmailAllowed((companySettings.getEmail() == 1) ? true : false);
		companySettingsModel.setStock((companySettings.getStock() == 1) ? true : false);
		companySettingsModel.setService((companySettings.getService() == 1) ? true : false);

		return companySettingsModel;
	}

	@Override
	@Transactional
	public void setAll(CompanySettingsModel settingsModel, int companyId) {

		Company company = companyDAO.getById(companyId);
		CompanySettings companySettings = companySettingsDAO.getByCompanyId(companyId);

		company.setLogoURL(settingsModel.getLogoUrl());
		company.setPhone(settingsModel.getPhone());
		company.setPhoneAlt(settingsModel.getPhoneAlt());
		company.setEmail(settingsModel.getEmail());
		company.setEmailAlt(settingsModel.getEmailAlt());
		company.setWeb(settingsModel.getWeb());

		companySettings.setNotes(settingsModel.getNotes());

		companySettings.setLogo(settingsModel.getLogo() ? 1 : 0);
		companySettings.setPhone(settingsModel.getPhoneAllowed() ? 1 : 0);
		companySettings.setEmail(settingsModel.getEmailAllowed() ? 1 : 0);
		companySettings.setStock(settingsModel.getStock() ? 1 : 0);
		companySettings.setService(settingsModel.getService() ? 1 : 0);

		companyDAO.update(company);
		companySettingsDAO.update(companySettings);
	}

}
