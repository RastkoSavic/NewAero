package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.StockPart;

public interface StockPartDAO {

	public void save(StockPart stockPart);

	public List<StockPart> getAll();

	public StockPart getById(int id);

	public void update(StockPart stockPart);

	public void delete(StockPart stockPart);

	public List<StockPart> findByPart(String pNumber);

	public List<StockPart> findLatest();
}
