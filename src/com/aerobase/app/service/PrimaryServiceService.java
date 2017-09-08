package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.PrimaryService;

public interface PrimaryServiceService {

	public void save(PrimaryService primaryService);

	public List<PrimaryService> getAll();

	public PrimaryService getById(int id);

	public void update(PrimaryService primaryService);

	public boolean delete(PrimaryService primaryService);
}
