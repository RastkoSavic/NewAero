package com.aerobase.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CompanySettingsModel {

	@Size(max = 255, message = "notes can\'r be longer than 500 characters")
	private String notes;

	private boolean logo;

	@Size(max = 255, message = "url can\'r be longer than 255 characters")
	private String logoUrl;

	private boolean phoneAllowed;

	@Pattern(regexp = "(^$|.{5,25})", message = "phone must be between 5 and 25 characters long")
	private String phone;

	@Pattern(regexp = "(^$|.{5,25})", message = "phone must be between 5 and 25 characters long")
	private String phoneAlt;

	private boolean emailAllowed;

	@NotNull(message = "email is required")
	@Size(min = 10, max = 255, message = "email must be between 10 and 255 characters long")
	private String email;

	@Pattern(regexp = "(^$|.{10,255})", message = "email must be between 10 and 255 characters long")
	private String emailAlt;

	@Pattern(regexp = "(^$|.{10,255})", message = "url must be between 10 and 255 characters long")
	private String web;

	private boolean stock;

	private boolean service;

	public CompanySettingsModel() {
	}

	public CompanySettingsModel(String notes, boolean logo, String logoUrl, boolean phoneAllowed, String phone,
			String phoneAlt, boolean emailAllowed, String email, String emailAlt, String web, boolean stock,
			boolean service) {
		this.notes = notes;
		this.logo = logo;
		this.logoUrl = logoUrl;
		this.phoneAllowed = phoneAllowed;
		this.phone = phone;
		this.phoneAlt = phoneAlt;
		this.emailAllowed = emailAllowed;
		this.email = email;
		this.emailAlt = emailAlt;
		this.web = web;
		this.stock = stock;
		this.service = service;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean getLogo() {
		return logo;
	}

	public void setLogo(boolean logo) {
		this.logo = logo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public boolean getPhoneAllowed() {
		return phoneAllowed;
	}

	public void setPhoneAllowed(boolean phoneAllowed) {
		this.phoneAllowed = phoneAllowed;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneAlt() {
		return phoneAlt;
	}

	public void setPhoneAlt(String phoneAlt) {
		this.phoneAlt = phoneAlt;
	}

	public boolean getEmailAllowed() {
		return emailAllowed;
	}

	public void setEmailAllowed(boolean emailAllowed) {
		this.emailAllowed = emailAllowed;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAlt() {
		return emailAlt;
	}

	public void setEmailAlt(String emailAlt) {
		this.emailAlt = emailAlt;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public boolean getStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public boolean getService() {
		return service;
	}

	public void setService(boolean service) {
		this.service = service;
	}

}
