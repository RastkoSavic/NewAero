package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.MessageTypeDAO;
import com.aerobase.app.entity.MessageType;

@Repository
public class MessageTypeDAOImpl implements MessageTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(MessageType messageType) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(messageType);
	}

	@Override
	public List<MessageType> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<MessageType> query = currentSession.createQuery("FROM MessageType", MessageType.class);

		List<MessageType> messageTypes = query.getResultList();

		return messageTypes;
	}

	@Override
	public MessageType getByCode(String code) {
		Session currentSession = sessionFactory.getCurrentSession();

		MessageType messageType = (MessageType) currentSession.load(MessageType.class, code);

		return messageType;
	}

	@Override
	public void update(MessageType messageType) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(messageType);
	}

	@Override
	public void delete(MessageType messageType) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(messageType);
	}

}
