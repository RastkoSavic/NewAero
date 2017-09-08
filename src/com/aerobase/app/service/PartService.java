package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Part;

public interface PartService {

	public void save(Part part);

	public List<Part> getAll();

	public Part getByPartNumber(String partNumber);

	public void update(Part part);

	public boolean delete(Part part);
}
