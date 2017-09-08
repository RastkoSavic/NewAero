package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.BillDAO;
import com.aerobase.app.entity.Bill;
import com.aerobase.app.service.BillService;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDAO billDAO;
	
	@Override
	@Transactional
	public void save(Bill bill) {

		billDAO.save(bill);
	}

	@Override
	@Transactional
	public List<Bill> getAll() {

		return billDAO.getAll();
	}

	@Override
	@Transactional
	public Bill getById(int id) {

		return billDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(Bill bill) {

		billDAO.update(bill);
	}

	@Override
	@Transactional
	public void delete(Bill bill) {

		billDAO.delete(bill);
	}

}
