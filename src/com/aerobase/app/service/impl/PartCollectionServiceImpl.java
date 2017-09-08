package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.PartCollectionDAO;
import com.aerobase.app.entity.PartCollection;
import com.aerobase.app.service.PartCollectionService;

@Service
public class PartCollectionServiceImpl implements PartCollectionService {

	@Autowired
	private PartCollectionDAO partCollectionDAO;
	
	@Override
	@Transactional
	public void save(PartCollection partCollection) {

		partCollectionDAO.save(partCollection);
	}

	@Override
	@Transactional
	public List<PartCollection> getAll() {

		return partCollectionDAO.getAll();
	}

	@Override
	@Transactional
	public PartCollection getById(int id) {

		return partCollectionDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(PartCollection partCollection) {

		partCollectionDAO.update(partCollection);
	}

	@Override
	@Transactional
	public void delete(PartCollection partCollection) {

		partCollectionDAO.delete(partCollection);
	}

}
