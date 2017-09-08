package com.aerobase.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserSettingsModel {

	private int id;

	@NotNull(message = "first name is required")
	@Size(min = 1, max = 45, message = "first name must be between 1 and 45 characters long")
	private String firstName;

	@NotNull(message = "last name is required")
	@Size(min = 1, max = 45, message = "last name must be between 1 and 45 characters long")
	private String lastName;

	@Pattern(regexp = "(^$|.{3,45})", message = "title must be between 3 and 45 characters long")
	private String position;

	@Pattern(regexp = "(^$|.{5,25})", message = "phone must be between 5 and 25 characters long")
	private String phone;

	@Pattern(regexp = "(^$|.{5,25})", message = "phone must be between 5 and 25 characters long")
	private String phoneAlt;

	@NotNull(message = "email is required")
	@Size(min = 10, max = 255, message = "email must be between 10 and 255 characters long")
	private String email;

	@Pattern(regexp = "(^$|.{10,255})", message = "email must be between 10 and 255 characters long")
	private String emailAlt;

	private boolean phoneAllowed;

	private boolean emailAllowed;

	private boolean messageAllowed;

	@Pattern(regexp = "(^$|.{1,255})", message = "notes must be between 1 and 255 characters long")
	private String notes;

	public UserSettingsModel() {
	}

	public UserSettingsModel(int id, String firstName, String lastName, String position, String phone, String phoneAlt,
			String email, String emailAlt, String notes) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.phone = phone;
		this.phoneAlt = phoneAlt;
		this.email = email;
		this.emailAlt = emailAlt;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public boolean isPhoneAllowed() {
		return phoneAllowed;
	}

	public void setPhoneAllowed(boolean phoneAllowed) {
		this.phoneAllowed = phoneAllowed;
	}

	public boolean isEmailAllowed() {
		return emailAllowed;
	}

	public void setEmailAllowed(boolean emailAllowed) {
		this.emailAllowed = emailAllowed;
	}

	public boolean isMessageAllowed() {
		return messageAllowed;
	}

	public void setMessageAllowed(boolean messageAllowed) {
		this.messageAllowed = messageAllowed;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
