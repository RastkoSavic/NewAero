package com.aerobase.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "part_conditions")
public class PartCondition {

	@Id
	@NotNull(message = "code is required")
	@Size(min = 2, max = 2, message = "code must be exactly 2 characters long")
	private String code; // max 2

	@NotNull(message = "condition name is required")
	@Size(min = 3, max = 20, message = "condition name must be between 3 and 20 characters long")
	private String name; // max 20

	@Size(max = 511, message = "description can\'t be longer than 500 characters")
	private String description; // max 255 null

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST }, mappedBy = "conditions")

	/*
	 * @JoinTable(name = "collection_conditions", inverseJoinColumns
	 * = @JoinColumn(name = "id_collection", nullable = false, updatable = false),
	 * joinColumns = @JoinColumn(name = "condition_code", nullable = false,
	 * updatable = false))
	 */
	private Set<PartCollection> collections = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "condition")
	private Set<StockPart> stockParts = new HashSet<>();

	// TODO DAO
	public PartCondition() {
	}

	public PartCondition(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
