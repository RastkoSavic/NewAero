package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.PartCategory;

public interface PartCategoryService {

	public void save(PartCategory category);

	public List<PartCategory> getAll();

	public PartCategory getByCategory(String name);

	public void update(PartCategory category);

	public boolean delete(PartCategory category);
}
