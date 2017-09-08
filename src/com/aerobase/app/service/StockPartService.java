package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.StockPart;
import com.aerobase.app.model.StockPartModel;

public interface StockPartService {

	public void save(StockPartModel stockPart, Integer variantId, Integer usstId);

	public List<StockPartModel> getAll();

	public StockPart getById(int id);

	public void update(StockPart stockPart);

	public void delete(StockPart stockPart);

	public List<StockPartModel> findResults(String pNumber);

	public List<StockPartModel> getLatest();
}
