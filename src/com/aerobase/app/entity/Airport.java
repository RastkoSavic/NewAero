package com.aerobase.app.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "airports")
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name; // max 100

	@Column(name = "iata_code")
	private String IATACode; // max 3, null

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airport")
	private Set<AOG> AOGSet;

	// TODO DAO
	public Airport() {
	}

	public Airport(String name) {
		this.name = name;
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

	public void setIATACode(String IATACode) {
		this.IATACode = IATACode;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<AOG> getAOGSet() {
		return AOGSet;
	}

	public void setAOGSet(Set<AOG> AOGSet) {
		this.AOGSet = AOGSet;
	}
}
