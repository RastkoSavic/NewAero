package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Message;

public interface MessageDAO {

	public int save(Message message);

	public List<Message> getAll();

	public Message getById(int id);

	public void update(Message message);

	public void delete(Message message);

	public List<Message> getAllForBoard();
}
