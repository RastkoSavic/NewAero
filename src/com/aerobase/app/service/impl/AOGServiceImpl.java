package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AOGDAO;
import com.aerobase.app.entity.AOG;
import com.aerobase.app.service.AOGService;

@Service
public class AOGServiceImpl implements AOGService {

	@Autowired
	private AOGDAO aogDAO;
	
	@Override
	@Transactional
	public void save(AOG aog) {

		aogDAO.save(aog);
	}

	@Override
	@Transactional
	public List<AOG> getAll() {

		return aogDAO.getAll();
	}

	@Override
	@Transactional
	public AOG getById(int id) {

		return aogDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(AOG aog) {

		aogDAO.update(aog);
	}

	@Override
	@Transactional
	public void delete(AOG aog) {

		aogDAO.delete(aog);
	}

}
