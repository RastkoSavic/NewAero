package com.aerobase.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterModel {

	@NotNull(message = "name is required")
	@Size(min = 3, max = 100, message = "company name must be between 3 and 100 characters long")
	private String name;

	@NotNull(message = "email is required")
	@Size(min = 10, max = 255, message = "company email must be between 10 and 255 characters long")
	private String companyEmail;

	@NotNull(message = "password is required")
	@Size(min = 4, max = 255, message = "password must be between 4 and 255 characters long")
	private String masterPass;

	@NotNull(message = "password repeat is required")
	@Size(min = 4, max = 255, message = "password must be between 4 and 255 characters long")
	private String repeatPass;

	@NotNull(message = "first name is required")
	@Size(min = 1, max = 45, message = "first name must be between 10 and 45 characters long")
	private String firstName;

	@NotNull(message = "last name is required")
	@Size(min = 1, max = 45, message = "last name must be between 10 and 45 characters long")
	private String lastName;

	public RegisterModel() {
	}

	public RegisterModel(String name, String companyEmail, String masterPass, String repeatPass, String firstName,
			String lastName) {
		this.name = name;
		this.companyEmail = companyEmail;
		this.masterPass = masterPass;
		this.repeatPass = repeatPass;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getMasterPass() {
		return masterPass;
	}

	public void setMasterPass(String masterPass) {
		this.masterPass = masterPass;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
