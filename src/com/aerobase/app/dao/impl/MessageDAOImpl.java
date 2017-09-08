package com.aerobase.app.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerobase.app.dao.MessageDAO;
import com.aerobase.app.entity.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(Message message) {
		Session currentSession = sessionFactory.getCurrentSession();

		Integer id = (Integer) currentSession.save(message);

		return id;
	}

	@Override
	public List<Message> getAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Message> query = currentSession.createQuery("FROM Message", Message.class);

		List<Message> messages = query.getResultList();

		for (Message message : messages) {

			Hibernate.initialize(message.getType());
			Hibernate.initialize(message.getSender());
			Hibernate.initialize(message.getAog());
		}
		return messages;
	}

	@Override
	public Message getById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Message message = (Message) currentSession.get(Message.class, id);

		return message;
	}

	@Override
	public void update(Message message) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(message);
	}

	@Override
	public void delete(Message message) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(message);
	}

	@Override
	public List<Message> getAllForBoard() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Message> query = currentSession.createQuery(
				"FROM Message as m where m.type.code='ADGM' or m.type.code='GNPM' or m.type.code='GNSM' or m.type.code='PAOM' or m.type.code='PAWM' order by created desc",
				Message.class);

		List<Message> messages = query.getResultList();

		for (Message message : messages) {

			Hibernate.initialize(message.getType());
			Hibernate.initialize(message.getSender());
		}
		return messages;
	}

}
