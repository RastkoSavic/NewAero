package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.CompanySettingsDAO;
import com.aerobase.app.entity.CompanySettings;

@Repository
public class CompanySettingsDAOImpl implements CompanySettingsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(CompanySettings companySettings) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(companySettings);
	}

	@Override
	public List<CompanySettings> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<CompanySettings> query = currentSession.createQuery("FROM CompanySettings", CompanySettings.class);

		List<CompanySettings> companySettingsList = query.getResultList();

		return companySettingsList;
	}

	@Override
	public CompanySettings getByCompanyId(int companyId) {
		Session currentSession = sessionFactory.getCurrentSession();

		CompanySettings companySettings = (CompanySettings) currentSession.load(CompanySettings.class, companyId);

		return companySettings;
	}

	@Override
	public void update(CompanySettings companySettings) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(companySettings);
	}

	@Override
	public void delete(CompanySettings companySettings) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(companySettings);
	}

}
