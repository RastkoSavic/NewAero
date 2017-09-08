package com.aerobase.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterAdminModel {

	@NotNull(message = "email is required")
	@Size(min = 10, max = 255, message = "email must be between 10 and 255 characters long")
	private String email;

	@NotNull(message = "password is required")
	@Size(min = 4, max = 255, message = "password must be between 4 and 255 characters long")
	private String password;

	@NotNull(message = "password repeat  is required")
	@Size(min = 4, max = 255, message = "password must be between 4 and 255 characters long")
	private String repeatPass;

	@NotNull(message = "first name is required")
	@Size(min = 1, max = 45, message = "last name must be between 2 and 45 characters long")
	private String firstName;

	@NotNull(message = "last name is required")
	@Size(min = 1, max = 45, message = "last name must be between 2 and 45 characters long")
	private String lastName;

	public RegisterAdminModel() {
	}

	public RegisterAdminModel(String email, String password, String repeatPass, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.repeatPass = repeatPass;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
