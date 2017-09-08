package com.aerobase.app.dao;

import java.util.List;

import com.aerobase.app.entity.Message;
import com.aerobase.app.entity.MessageRecipient;

public interface MessageRecipientDAO {

	public void save(MessageRecipient recipient);

	public List<MessageRecipient> getAll();

	public MessageRecipient getById(int id);

	public void update(MessageRecipient recipient);

	public void delete(MessageRecipient recipient);

	public List<Message> getMessages(int id);
}
