package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.PartCondition;

public interface PartConditionDAO {

	public void save(PartCondition condition);

	public List<PartCondition> getAll();

	public PartCondition getByCode(String code);

	public void update(PartCondition condition);

	public void delete(PartCondition condition);
}
