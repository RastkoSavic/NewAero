package com.aerobase.app.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "aircraft_variants")
public class AircraftVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "variant is required")
	@Size(min = 1, max = 20, message = "variant must be between 3 and 20 characters long")
	private String variant; // max 20

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne()
	@JoinColumn(name = "model")
	private AircraftModel model;

	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "aircraftVariants")
	private Set<Part> parts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraftVariant")
	private Set<AOG> AOGSet;

	// TODO DAO
	public AircraftVariant() {
	}

	public AircraftVariant(String variant) {
		this.variant = variant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public AircraftModel getModel() {
		return model;
	}

	public void setModel(AircraftModel model) {
		this.model = model;
	}

	public Set<Part> getParts() {
		return parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}

	public Set<AOG> getAOGSet() {
		return AOGSet;
	}

	public void setAOGSet(Set<AOG> AOGSet) {
		this.AOGSet = AOGSet;
	}
}
