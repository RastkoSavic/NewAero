package com.aerobase.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.MessageRecipientDAO;
import com.aerobase.app.entity.Message;
import com.aerobase.app.entity.MessageRecipient;

@Repository
public class MessageRecipientDAOImpl implements MessageRecipientDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(MessageRecipient recipient) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(recipient);
	}

	@Override
	public List<MessageRecipient> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<MessageRecipient> query = currentSession.createQuery("FROM MessageRecipient", MessageRecipient.class);

		List<MessageRecipient> recipients = query.getResultList();

		return recipients;
	}

	@Override
	public MessageRecipient getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		MessageRecipient recipient = (MessageRecipient) currentSession.load(MessageRecipient.class, id);

		return recipient;
	}

	@Override
	public void update(MessageRecipient recipient) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(recipient);
	}

	@Override
	public void delete(MessageRecipient recipient) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(recipient);
	}

	@Override
	public List<Message> getMessages(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		List<Message> messages = new ArrayList<>();
		Query<MessageRecipient> query = currentSession.createQuery("FROM MessageRecipient mr where mr.recipient=" + id + " order by created", MessageRecipient.class);
		List<MessageRecipient> recipients = query.getResultList();
		
		for (MessageRecipient messageRecipient : recipients) {
			messages.add(messageRecipient.getMessage());
		}
		
		return messages;
	}

}
