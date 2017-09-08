package com.aerobase.app.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "part_categories")
public class PartCategory {

	@Id
	@NotNull(message = "category is required")
	@Size(min = 3, max = 20, message = "type must be between 3 and 20 characters long")
	private String category; // max 20

	@Size(max = 500, message = "description can\'t be longer than 500 characters")
	private String description; // max 255, null

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private Set<Part> parts;

	// TODO DAO
	// For Admin Create, Update, Delete, Select all, select by category
	// User filter by category top/side
	// On create use constructor, optionaly set description
	// On update get by category and update
	// Delete on SuperAdmin, prompt!!!
	public PartCategory() {
	}

	public PartCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Set<Part> getParts() {
		return parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}
}
