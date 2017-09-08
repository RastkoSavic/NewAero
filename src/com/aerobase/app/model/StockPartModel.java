package com.aerobase.app.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StockPartModel {

	private int id;

	@NotNull(message = "is required")
	@Size(min = 3, max = 25, message = "part number must be between 3 and 25 characters long")
	private String partNumber;

	@NotNull(message = "description is required")
	@Size(min = 3, max = 50, message = "description must be between 3 and 50 characters long")
	private String description;

	@NotNull(message = "quantity is required")
	@Min(value = 1, message = "quantity can\'t be lower than 0")
	private Integer quantity;

	@Min(value = 1, message = "price can\'t be lower than 0")
	private Float price;

	private String variant;

	@Pattern(regexp = "(^$|.{1,255})", message = "notes must be between 1 and 255 characters long")
	private String notes;

	private String category;

	private String condition;

	private String conditionCode;

	private String company;

	private int companyId;

	private Date created;

	private int userId;

	public StockPartModel() {
	}

	public StockPartModel(String partNumber, String description, Integer quantity, Float price, String variant,
			String notes) {
		this.partNumber = partNumber;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.variant = variant;
		this.notes = notes;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConditionCode() {
		return conditionCode;
	}

	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
