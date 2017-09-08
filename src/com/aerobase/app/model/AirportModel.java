package com.aerobase.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AirportModel {

	private int id;

	@NotNull(message = "name is required")
	@Size(min = 5, max = 100, message = "name must be between 5 and 100 characters long")
	private String name;

	@NotNull(message = "iata code is required")
	@Size(min = 3, max = 3, message = "iata code must be exactly 3 characters long")
	private String IATACode;

	@NotNull(message = "address is required")
	@Size(min = 5, max = 255, message = "address must be between 5 and 255 characters long")
	private String address;

	public AirportModel() {
	}

	public AirportModel(int id, String name, String iATACode, String address) {
		this.id = id;
		this.name = name;
		this.IATACode = iATACode;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIATACode() {
		return IATACode;
	}

	public void setIATACode(String iATACode) {
		this.IATACode = iATACode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
