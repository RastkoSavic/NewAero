package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.MessageType;

public interface MessageTypeService {

	public void save(MessageType messageType);

	public List<MessageType> getAll();

	public MessageType getByCode(String code);

	public void update(MessageType messageType);

	public boolean delete(MessageType messageType);
}
