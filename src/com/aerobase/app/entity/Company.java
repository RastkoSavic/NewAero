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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name; // max 100

	private String phone; // max 25 null

	@Column(name = "phone_alt")
	private String phoneAlt; // max 25 null

	private String email; // max 255

	@Column(name = "email_alt")
	private String emailAlt; // max 255 null

	@Column(name = "master_pass")
	private String masterPass;// max 255

	private String web; // max 255 null

	@Column(name = "logo_url")
	private String logoURL; // max 255 null

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne()
	@JoinColumn(name = "primary_service")
	private PrimaryService primaryService;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "company")
	private CompanySettings settings;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "company")
	private CompanyStock stock;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "company_addresses", 
		joinColumns = {
			@JoinColumn(name = "id_company", nullable = false, updatable = false) }, 
		inverseJoinColumns = {
			@JoinColumn(name = "id_address", nullable = false, updatable = false) })
	private Set<Address> addresses = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Set<User> users = new HashSet<>();

	// TODO DAO
	public Company() {
	}

	public Company(String name, String email, String masterPass) {
		this.name = name;
		this.email = email;
		this.masterPass = masterPass;
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

	public String getMasterPass() {
		return masterPass;
	}

	public void setMasterPass(String masterPass) {
		this.masterPass = masterPass;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public PrimaryService getPrimaryService() {
		return primaryService;
	}

	public void setPrimaryService(PrimaryService primaryService) {
		this.primaryService = primaryService;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public CompanySettings getSettings() {
		return settings;
	}

	public void setSettings(CompanySettings settings) {
		this.settings = settings;
	}

	public CompanyStock getStock() {
		return stock;
	}

	public void setStock(CompanyStock stock) {
		this.stock = stock;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
