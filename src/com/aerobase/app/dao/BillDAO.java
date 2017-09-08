package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Bill;

public interface BillDAO {

	public void save(Bill bill);

	public List<Bill> getAll();

	public Bill getById(int id);

	public void update(Bill bill);

	public void delete(Bill bill);
}
