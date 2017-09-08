package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.PartCollection;

public interface PartCollectionDAO {

	public void save(PartCollection partCollection);

	public List<PartCollection> getAll();

	public PartCollection getById(int id);

	public void update(PartCollection partCollection);

	public void delete(PartCollection partCollection);
}