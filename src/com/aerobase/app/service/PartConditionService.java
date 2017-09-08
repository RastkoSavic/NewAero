package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.PartCondition;

public interface PartConditionService {

	public void save(PartCondition condition);

	public List<PartCondition> getAll();

	public PartCondition getByCode(String code);

	public void update(PartCondition condition);

	public boolean delete(PartCondition condition);
}
