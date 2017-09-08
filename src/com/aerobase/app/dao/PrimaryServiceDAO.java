package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.PrimaryService;

public interface PrimaryServiceDAO {

	public void save(PrimaryService primaryService);

	public List<PrimaryService> getAll();

	public PrimaryService getById(int id);

	public void update(PrimaryService primaryService);

	public void delete(PrimaryService primaryService);
}
