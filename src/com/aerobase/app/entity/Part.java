package com.aerobase.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "parts")
public class Part {

	@Id
	@Column(name = "part_number")
	@NotNull(message = "part number is required")
	@Size(min = 3, max = 25, message = "part number must be between 3 and 25 characters long")
	private String partNumber;// max 25

	@NotNull(message = "description is required")
	@Size(min = 3, max = 50, message = "description must be between 3 and 50 characters long")
	private String description; // max 50

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne()
	@JoinColumn(name = "category")
	private PartCategory category;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "parts_air_variants", joinColumns = {
			@JoinColumn(name = "part_number", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "variant_id", nullable = false, updatable = false) })
	private Set<AircraftVariant> aircraftVariants = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "part")
	private Set<PartCollection> collections = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "part")
	private Set<StockPart> stockParts = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pk.part")
	private Set<CompanySeeking> companySeekings = new HashSet<>(0);

	// TODO DAO
	public Part() {
	}

	public Part(String partNumber, String description) {
		this.partNumber = partNumber;
		this.description = description;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public PartCategory getCategory() {
		return category;
	}

	public void setCategory(PartCategory category) {
		this.category = category;
	}

	public Set<AircraftVariant> getAircraftVariants() {
		return aircraftVariants;
	}

	public void setAircraftVariants(Set<AircraftVariant> aircraftVariants) {
		this.aircraftVariants = aircraftVariants;
	}

	public Set<PartCollection> getCollections() {
		return collections;
	}

	public void setCollections(Set<PartCollection> collections) {
		this.collections = collections;
	}

	public Set<StockPart> getStockParts() {
		return stockParts;
	}

	public void setStockParts(Set<StockPart> stockParts) {
		this.stockParts = stockParts;
	}

	public Set<CompanySeeking> getCompanySeekings() {
		return companySeekings;
	}

	public void setCompanySeekings(Set<CompanySeeking> companySeekings) {
		this.companySeekings = companySeekings;
	}
}
