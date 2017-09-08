package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.AOG;

public interface AOGDAO {

	public void save(AOG aog);

	public List<AOG> getAll();

	public AOG getById(int id);

	public void update(AOG aog);

	public void delete(AOG aog);
}
