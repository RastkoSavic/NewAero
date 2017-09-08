package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.BillDAO;
import com.aerobase.app.entity.Bill;

@Repository
public class BillDAOImpl implements BillDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Bill bill) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(bill);
	}

	@Override
	public List<Bill> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Bill> query = currentSession.createQuery("FROM Bill", Bill.class);

		List<Bill> bills = query.getResultList();

		return bills;
	}

	@Override
	public Bill getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Bill bill = (Bill) currentSession.load(Bill.class, id);

		return bill;
	}

	@Override
	public void update(Bill bill) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(bill);
	}

	@Override
	public void delete(Bill bill) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(bill);
	}

}
