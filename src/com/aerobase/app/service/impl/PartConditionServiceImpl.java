package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.PartConditionDAO;
import com.aerobase.app.entity.PartCondition;
import com.aerobase.app.service.PartConditionService;

@Service
public class PartConditionServiceImpl implements PartConditionService {

	@Autowired
	private PartConditionDAO partConditionDAO;
	
	@Override
	@Transactional
	public void save(PartCondition condition) {

		partConditionDAO.save(condition);
	}

	@Override
	@Transactional
	public List<PartCondition> getAll() {

		return partConditionDAO.getAll();
	}

	@Override
	@Transactional
	public PartCondition getByCode(String code) {

		return partConditionDAO.getByCode(code);
	}

	@Override
	@Transactional
	public void update(PartCondition condition) {

		partConditionDAO.update(condition);
	}

	@Override
	@Transactional
	public boolean delete(PartCondition condition) {

		PartCondition pc = partConditionDAO.getByCode(condition.getCode());
		if (pc.getCollections().size() > 0 || pc.getStockParts().size() > 0) {
			
			return false;
		} else {
			partConditionDAO.delete(pc);
			
			return true;
		}
		
	}

}
