package com.aerobase.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.MessageTypeDAO;
import com.aerobase.app.entity.MessageType;
import com.aerobase.app.service.MessageTypeService;

@Service
public class MessageTypeServiceImpl implements MessageTypeService {

	@Autowired
	private MessageTypeDAO messageTypeDAO;

	@Override
	@Transactional
	public void save(MessageType messageType) {

		messageTypeDAO.save(messageType);
	}

	@Override
	@Transactional
	public List<MessageType> getAll() {

		return messageTypeDAO.getAll();
	}

	@Override
	@Transactional
	public MessageType getByCode(String code) {

		return messageTypeDAO.getByCode(code);
	}

	@Override
	@Transactional
	public void update(MessageType messageType) {

		messageTypeDAO.update(messageType);
	}

	@Override
	@Transactional
	public boolean delete(MessageType messageType) {

		MessageType mt = messageTypeDAO.getByCode(messageType.getCode());

		if (mt.getMessages().size() > 0) {

			return false;
		} else {
			messageTypeDAO.delete(mt);

			return true;
		}

	}

}
