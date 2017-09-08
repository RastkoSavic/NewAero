package com.aerobase.app.model;

public class ResponseModel {

	private int senderId;

	private int recieverId;

	private int messageId;

	public ResponseModel() {

	}

	public ResponseModel(int senderId, int recieverId, int messageId) {
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.messageId = messageId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(int recieverId) {
		this.recieverId = recieverId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

}
