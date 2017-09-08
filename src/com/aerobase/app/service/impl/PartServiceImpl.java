package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.PartDAO;
import com.aerobase.app.entity.Part;
import com.aerobase.app.service.PartService;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartDAO partDAO;

	@Override
	@Transactional
	public void save(Part part) {

		partDAO.save(part);
	}

	@Override
	@Transactional
	public List<Part> getAll() {

		return partDAO.getAll();
	}

	@Override
	@Transactional
	public Part getByPartNumber(String partNumber) {

		return partDAO.getByPartNumber(partNumber);
	}

	@Override
	@Transactional
	public void update(Part part) {

		partDAO.update(part);
	}

	@Override
	@Transactional
	public boolean delete(Part part) {

		Part p = partDAO.getByPartNumber(part.getPartNumber());
		if (p.getCollections().size() > 0 || p.getStockParts().size() > 0) {

			return false;
		} else {
			partDAO.delete(p);

			return true;
		}

	}

}
