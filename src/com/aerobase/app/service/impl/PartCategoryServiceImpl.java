package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.PartCategoryDAO;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.service.PartCategoryService;

@Service
public class PartCategoryServiceImpl implements PartCategoryService {

	@Autowired
	private PartCategoryDAO categoryDAO;

	@Override
	@Transactional
	public void save(PartCategory category) {

		if (category.getDescription().isEmpty()) {
			category.setDescription(null);
		}
		
		categoryDAO.save(category);
	}

	@Override
	@Transactional
	public List<PartCategory> getAll() {

		return categoryDAO.getAll();
	}

	@Override
	@Transactional
	public PartCategory getByCategory(String name) {

		return categoryDAO.getByCategory(name);
	}

	@Override
	@Transactional
	public void update(PartCategory category) {

		if (category.getDescription().isEmpty()) {
			category.setDescription(null);
		}
		
		categoryDAO.update(category);
	}

	@Override
	@Transactional
	public boolean delete(PartCategory category) {
		
		PartCategory cat = categoryDAO.getByCategory(category.getCategory());
		
		if (cat.getParts().size() > 0) {
			
			return false;
		} else {
			categoryDAO.delete(cat);
			
			return true;
		}
		
	}

}
