package com.aerobase.app.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MessageModel {

	private int id;

	@NotNull(message = "message is required")
	@Size(min = 2, max = 255, message = "message must be between 2 and 255 characters long")
	private String message;

	private Date created;

	private String type;

	private String typeCode;

	private int userId;

	private String firstName;

	private String lastName;

	private int companyId;

	private String company;

	private int recipientId;

	@NotNull(message = "part number is required")
	@Size(min = 3, max = 25, message = "part number must be between 3 and 25 characters long")
	private String partNumber;

	@NotNull(message = "description is required")
	@Size(min = 3, max = 50, message = "description must be between 3 and 50 characters long")
	private String description;

	private String category;

	private String condition;

	private String conditionCode;

	private List<String> conditions;

	@NotNull(message = "quantity is required")
	@Min(value = 1, message = "quantity can\'t be lower than 0")
	private int quantity;

	@NotNull(message = "price is required")
	@Min(value = 1, message = "price can\'t be lower than 0")
	private Float price;

	@Pattern(regexp = "(^$|.{1,255})", message = "notes must be between 1 and 255 characters long")
	private String notes;

	@Pattern(regexp = "(^$|.{1,20})", message = "aog status must be between 1 and 255 characters long")
	private String aogStatus;

	@Pattern(regexp = "(^$|.{1,255})", message = "aog notes must be between 1 and 255 characters long")
	private String aogNotes;

	private int modelId;

	private String variant;

	private int variantId;

	private int airpotId;

	private String airport;

	private String aiportIATA;

	private String country;

	private String city;

	private String addressId;

	private String address;

	public MessageModel() {
	}

	public MessageModel(int id, String message, Date created, String type, String typeCode, int userId,
			String firstName, String lastName, int companyId, String company) {
		this.id = id;
		this.message = message;
		this.created = created;
		this.type = type;
		this.typeCode = typeCode;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyId = companyId;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
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

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAogStatus() {
		return aogStatus;
	}

	public void setAogStatus(String aogStatus) {
		this.aogStatus = aogStatus;
	}

	public String getAogNotes() {
		return aogNotes;
	}

	public void setAogNotes(String aogNotes) {
		this.aogNotes = aogNotes;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public int getVariantId() {
		return variantId;
	}

	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}

	public int getAirpotId() {
		return airpotId;
	}

	public void setAirpotId(int airpotId) {
		this.airpotId = airpotId;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public String getAiportIATA() {
		return aiportIATA;
	}

	public void setAiportIATA(String aiportIATA) {
		this.aiportIATA = aiportIATA;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
