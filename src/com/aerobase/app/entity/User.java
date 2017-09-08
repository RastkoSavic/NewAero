package com.aerobase.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	@NotNull(message = "first name is required")
	@Size(min = 1, max = 45, message = "first name must be between 1 and 45 characters long")
	private String firstName; // max 45

	@Column(name = "last_name")
	@NotNull(message = "last name is required")
	@Size(min = 1, max = 45, message = "last name must be between 1 and 45 characters long")
	private String lastName; // max 45

	@Size(max = 45, message = "title can\'t be longer than 45 characters")
	private String position; // max 45 null

	private String phone; // max 25 null

	@Column(name = "phone_alt")
	private String phoneAlt; // max 25 null

	@NotNull(message = "email is required")
	@Size(min = 10, max = 255, message = "email must be between 10 and 255 characters long")
	private String email; // max 255

	@Column(name = "email_alt")
	private String emailAlt; // max 255 null

	@NotNull(message = "password is required")
	@Size(min = 4, max = 255, message = "password must be between 4 and 255 characters long")
	private String password; // max 255

	@Column(name = "logged_in")
	private byte logedIn; // null

	@Temporal(TemporalType.DATE)
	private Date activity; // null

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne()
	@JoinColumn(name = "company")
	private Company company;

	@ManyToOne()
	@JoinColumn(name = "status")
	private UserStatus status;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private UserSettings settings; // null

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
	private Set<Message> messages;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient")
	private Set<MessageRecipient> recipientIN;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<StockPart> stockParts = new HashSet<>();

	// TODO DAO
	public User() {
	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getLogedIn() {
		return logedIn;
	}

	public void setLogedIn(byte logedIn) {
		this.logedIn = logedIn;
	}

	public Date getActivity() {
		return activity;
	}

	public void setActivity(Date activity) {
		this.activity = activity;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserSettings getSettings() {
		return settings;
	}

	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<MessageRecipient> getRecipientIN() {
		return recipientIN;
	}

	public void setRecipientIN(Set<MessageRecipient> recipientIN) {
		this.recipientIN = recipientIN;
	}

	public Set<StockPart> getStockParts() {
		return stockParts;
	}

	public void setStockParts(Set<StockPart> stockParts) {
		this.stockParts = stockParts;
	}
}
