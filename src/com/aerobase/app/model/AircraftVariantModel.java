package com.aerobase.app.model;

public class AircraftVariantModel {

	private int id;

	private String variant;

	public AircraftVariantModel() {
	}

	public AircraftVariantModel(int id, String variant) {
		this.id = id;
		this.variant = variant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

}
