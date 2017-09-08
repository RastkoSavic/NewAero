package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Part;

public interface PartDAO {

	public void save(Part part);

	public List<Part> getAll();

	public Part getByPartNumber(String partNumber);

	public void update(Part part);

	public void delete(Part part);
}
