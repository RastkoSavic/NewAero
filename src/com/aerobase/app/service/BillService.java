package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Bill;

public interface BillService {

	public void save(Bill bill);

	public List<Bill> getAll();

	public Bill getById(int id);

	public void update(Bill bill);

	public void delete(Bill bill);
}
