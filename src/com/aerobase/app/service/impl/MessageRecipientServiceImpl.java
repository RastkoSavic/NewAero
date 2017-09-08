package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.MessageRecipientDAO;
import com.aerobase.app.entity.Message;
import com.aerobase.app.entity.MessageRecipient;
import com.aerobase.app.service.MessageRecipientService;

@Service
public class MessageRecipientServiceImpl implements MessageRecipientService {

	@Autowired
	private MessageRecipientDAO messageRecipientDAO;
	
	@Override
	@Transactional
	public void save(MessageRecipient recipient) {

		messageRecipientDAO.save(recipient);
	}

	@Override
	@Transactional
	public List<MessageRecipient> getAll() {

		return messageRecipientDAO.getAll();
	}

	@Override
	@Transactional
	public MessageRecipient getById(int id) {

		return messageRecipientDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(MessageRecipient recipient) {

		messageRecipientDAO.update(recipient);
	}

	@Override
	@Transactional
	public void delete(MessageRecipient recipient) {

		messageRecipientDAO.delete(recipient);
	}

	@Override
	@Transactional
	public List<Message> getMessages(int id) {

		List<Message> messages = messageRecipientDAO.getMessages(id);
		return messages;
	}

}
