package com.aerobase.app.model;

public class CityModel {

	private int id;

	private String name; // max 45

	private String stateCode; // max 2, null

	private String stateName; // max 20, null

	public CityModel() {
	}

	
	public CityModel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public CityModel(int id, String name, String stateCode, String stateName) {
		this.id = id;
		this.name = name;
		this.stateCode = stateCode;
		this.stateName = stateName;
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

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
