package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.PartCategory;

public interface PartCategoryDAO {

	public void save(PartCategory category);

	public List<PartCategory> getAll();

	public PartCategory getByCategory(String name);

	public void update(PartCategory category);

	public void delete(PartCategory category);
}
