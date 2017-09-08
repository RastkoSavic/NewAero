package com.aerobase.app.model;

public class UserModel {

	private int id;

	private String status;

	private int companyId;

	public UserModel() {
	}

	public UserModel(int id, String status, int companyId) {
		this.id = id;
		this.status = status;
		this.companyId = companyId;
	}

	public UserModel(int id, String status) {
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}
