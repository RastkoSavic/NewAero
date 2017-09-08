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
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message="message is required")
	@Size(min = 2, max = 500, message = "message must be between 2 and 500 characters long")
	private String message; // max 255

	@Column(name = "doc_url")
	private String docURL; // max 255, null

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne()
	@JoinColumn(name = "type")
	private MessageType type;

	@ManyToOne()
	@JoinColumn(name = "sender")
	private User sender;

	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "message", optional = true)
	private MessageRecipient recipient; // null

	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "message", optional = true)
	private AOG aog;

	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "message", optional = true)
	private Bill bill;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
	private Set<PartCollection> partCollections = new HashSet<>();

	// TODO DAO
	public Message() {
	}

	public Message(String message) {
		this.message = message;
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

	public String getDocURL() {
		return docURL;
	}

	public void setDocURL(String docURL) {
		this.docURL = docURL;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public MessageRecipient getRecipient() {
		return recipient;
	}

	public void setRecipient(MessageRecipient recipient) {
		this.recipient = recipient;
	}

	public AOG getAog() {
		return aog;
	}

	public void setAog(AOG aog) {
		this.aog = aog;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Set<PartCollection> getPartCollections() {
		return partCollections;
	}

	public void setPartCollections(Set<PartCollection> partCollections) {
		this.partCollections = partCollections;
	}
}
