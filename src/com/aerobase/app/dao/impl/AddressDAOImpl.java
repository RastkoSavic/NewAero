package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.AddressDAO;
import com.aerobase.app.entity.Address;

@Repository
public class AddressDAOImpl implements AddressDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Address address) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(address);
	}

	@Override
	public List<Address> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Address> query = currentSession.createQuery("FROM Address", Address.class);

		List<Address> addresses = query.getResultList();

		for (Address address : addresses) {
			
			Hibernate.initialize(address.getCity());
		}

		return addresses;
	}

	@Override
	public Address getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Address address = (Address) currentSession.get(Address.class, id);

		return address;
	}

	@Override
	public void update(Address address) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(address);
	}

	@Override
	public void delete(Address address) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(address);
	}

}
