package com.aerobase.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginModel {

	@NotNull(message = "email is required")
	@Size(min = 10, max = 255, message = "email must be between 10 and 255 characters long")
	private String email;

	@NotNull(message = "is required")
	@Size(min = 4, max = 255, message = "type must be between 4 and 255 characters long")
	private String password;

	public LoginModel() {
	}

	public LoginModel(String email, String password) {
		this.email = email;
		this.password = password;
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

}
