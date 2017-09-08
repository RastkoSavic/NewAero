package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.MessageType;

public interface MessageTypeDAO {

	public void save(MessageType messageType);

	public List<MessageType> getAll();

	public MessageType getByCode(String code);

	public void update(MessageType messageType);

	public void delete(MessageType messageType);
}
