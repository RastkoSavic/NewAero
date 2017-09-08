package com.aerobase.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AOGDAO;
import com.aerobase.app.dao.AircraftVariantDAO;
import com.aerobase.app.dao.AirportDAO;
import com.aerobase.app.dao.MessageDAO;
import com.aerobase.app.dao.MessageTypeDAO;
import com.aerobase.app.dao.PartCategoryDAO;
import com.aerobase.app.dao.PartCollectionDAO;
import com.aerobase.app.dao.PartConditionDAO;
import com.aerobase.app.dao.PartDAO;
import com.aerobase.app.dao.UserDAO;
import com.aerobase.app.entity.AOG;
import com.aerobase.app.entity.AircraftVariant;
import com.aerobase.app.entity.Airport;
import com.aerobase.app.entity.Message;
import com.aerobase.app.entity.MessageType;
import com.aerobase.app.entity.Part;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.entity.PartCollection;
import com.aerobase.app.entity.PartCondition;
import com.aerobase.app.entity.User;
import com.aerobase.app.model.MessageModel;
import com.aerobase.app.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private MessageTypeDAO messageTypeDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PartDAO partDAO;

	@Autowired
	private PartCategoryDAO partCategoryDAO;

	@Autowired
	private PartConditionDAO partConditionDAO;

	@Autowired
	private PartCollectionDAO partCollectionDAO;

	@Autowired
	private AircraftVariantDAO aircraftVariantDAO;

	@Autowired
	private AirportDAO airportDAO;

	@Autowired
	private AOGDAO aogDAO;

	@Override
	@Transactional
	public int save(Message message) {

		int id = messageDAO.save(message);

		return id;
	}

	@Override
	@Transactional
	public List<Message> getAll() {

		return messageDAO.getAll();
	}

	@Override
	@Transactional
	public Message getById(int id) {

		return messageDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(Message message) {

		messageDAO.update(message);
	}

	@Override
	@Transactional
	public boolean delete(Message message) {

		Message m = messageDAO.getById(message.getId());

		if (m.getPartCollections().size() > 0 || m.getRecipient() != null) {

			return false;
		} else {
			messageDAO.delete(m);

			return true;
		}

	}

	@Override
	public List<MessageModel> findAllModels() {

		List<MessageModel> messageModels = new ArrayList<>();
		List<Message> messages = messageDAO.getAllForBoard();
		for (Message message : messages) {
			MessageModel messageModel = new MessageModel(message.getId(), message.getMessage(), message.getCreated(),
					message.getType().getType(), message.getType().getCode(), message.getSender().getId(),
					message.getSender().getFirstName(), message.getSender().getLastName(),
					message.getSender().getCompany().getId(), message.getSender().getCompany().getName());
			AOG aog;
			if (message.getAog() != null) {
				aog = message.getAog();
				messageModel.setAiportIATA(aog.getAirport().getIATACode());
				messageModel.setAirport(aog.getAirport().getName());
				messageModel.setAirpotId(aog.getAirport().getId());
				messageModel.setAogNotes(aog.getNotes());
				messageModel.setAogStatus(aog.getStatus());

				messageModel.setCity(aog.getAirport().getAddress().getCity().getName());
				messageModel.setCountry(aog.getAirport().getAddress().getCity().getCountry().getName());
				messageModel.setVariant(aog.getAircraftVariant().getVariant());
				messageModel.setVariantId(aog.getAircraftVariant().getId());
			}

			PartCollection collection;
			Part part;
			PartCondition condition;
			if (message.getPartCollections() != null && !message.getPartCollections().isEmpty()) {
				collection = message.getPartCollections().iterator().next();
				part = collection.getPart();
				condition = collection.getConditions().iterator().next();
				messageModel.setCategory(part.getCategory().getCategory());
				messageModel.setCondition(condition.getName());
				messageModel.setConditionCode(condition.getCode());
				messageModel.setDescription(part.getDescription());
				messageModel.setNotes(collection.getNotes());
				messageModel.setPartNumber(part.getPartNumber());
				messageModel.setPrice(collection.getPrice());
				messageModel.setQuantity(collection.getQuantity());
			}
			messageModels.add(messageModel);
		}

		return messageModels;
	}

	@Override
	public void postAdd(MessageModel postGNSM) {

		Message message = new Message(postGNSM.getMessage());
		
		MessageType messageType = messageTypeDAO.getByCode(postGNSM.getTypeCode());
		
		User sender = userDAO.getById(postGNSM.getUserId());
		
		message.setSender(sender);
		
		message.setType(messageType);

		messageDAO.save(message);
	}

	@Override
	public void postOfferAdd(MessageModel postPAOM) {

		Part part;

		part = partDAO.getByPartNumber(postPAOM.getPartNumber());

		if (part == null) {
			part = new Part(postPAOM.getPartNumber(), postPAOM.getDescription());
			PartCategory category = partCategoryDAO.getByCategory(postPAOM.getCategory());
			part.setCategory(category);
			partDAO.save(part);
		}

		if (postPAOM.getVariantId() > 0) {

			AircraftVariant aircraftVariant = aircraftVariantDAO.getById(postPAOM.getVariantId());
			aircraftVariant.getParts().add(part);
			part.getAircraftVariants().add(aircraftVariant);
			aircraftVariantDAO.update(aircraftVariant);
			partDAO.update(part);
		}

		PartCondition condition = partConditionDAO.getByCode(postPAOM.getConditionCode());

		Message message = new Message(postPAOM.getMessage());
		MessageType messageType = messageTypeDAO.getByCode(postPAOM.getTypeCode());
		User sender = userDAO.getById(postPAOM.getUserId());

		message.setType(messageType);
		messageType.getMessages().add(message);
		message.setSender(sender);
		sender.getMessages().add(message);

		PartCollection collection = new PartCollection(postPAOM.getQuantity());

		collection.getConditions().add(condition);
		condition.getCollections().add(collection);
		collection.setMessage(message);
		message.getPartCollections().add(collection);
		collection.setPart(part);
		part.getCollections().add(collection);
		collection.setPrice(postPAOM.getPrice());
		collection.setNotes(postPAOM.getNotes());

		messageDAO.save(message);
		partCollectionDAO.save(collection);

	}

	@Override
	public void postWantedAdd(MessageModel postPAWM) {

		Part part;

		part = partDAO.getByPartNumber(postPAWM.getPartNumber());

		if (part == null) {
			part = new Part(postPAWM.getPartNumber(), postPAWM.getDescription());
			PartCategory category = partCategoryDAO.getByCategory(postPAWM.getCategory());
			part.setCategory(category);
			partDAO.save(part);
		}

		PartCondition condition = partConditionDAO.getByCode(postPAWM.getConditionCode());

		Message message = new Message(postPAWM.getMessage());
		MessageType messageType = messageTypeDAO.getByCode(postPAWM.getTypeCode());
		User sender = userDAO.getById(postPAWM.getUserId());

		message.setType(messageType);
		messageType.getMessages().add(message);
		message.setSender(sender);
		sender.getMessages().add(message);

		PartCollection collection = new PartCollection(postPAWM.getQuantity());

		collection.getConditions().add(condition);
		condition.getCollections().add(collection);
		collection.setMessage(message);
		message.getPartCollections().add(collection);
		collection.setPart(part);
		part.getCollections().add(collection);
		collection.setPrice(postPAWM.getPrice());
		collection.setNotes(postPAWM.getNotes());

		messageDAO.save(message);

		partCollectionDAO.save(collection);

		if (postPAWM.getVariantId() > 0 && postPAWM.getAirpotId() > 0) {

			AircraftVariant aircraftVariant = aircraftVariantDAO.getById(postPAWM.getVariantId());

			Airport airport = airportDAO.getById(postPAWM.getAirpotId());

			AOG aog = new AOG(postPAWM.getAogStatus());
			aog.setNotes(postPAWM.getAogNotes());
			aog.setAircraftVariant(aircraftVariant);
			aircraftVariant.getAOGSet().add(aog);
			aog.setAirport(airport);
			airport.getAOGSet().add(aog);
			aog.setMessage(message);
			message.setAog(aog);
			aogDAO.save(aog);

		}
	}

	@Override
	public List<MessageModel> findAOGModels() {
		List<MessageModel> messageModels = new ArrayList<>();
		List<Message> messages = messageDAO.getAllForBoard();
		for (Message message : messages) {
			if (message.getAog() != null) {
				MessageModel messageModel = new MessageModel(message.getId(), message.getMessage(),
						message.getCreated(), message.getType().getType(), message.getType().getCode(),
						message.getSender().getId(), message.getSender().getFirstName(),
						message.getSender().getLastName(), message.getSender().getCompany().getId(),
						message.getSender().getCompany().getName());
				AOG aog;
				aog = message.getAog();
				messageModel.setAiportIATA(aog.getAirport().getIATACode());
				messageModel.setAirport(aog.getAirport().getName());
				messageModel.setAirpotId(aog.getAirport().getId());
				messageModel.setAogNotes(aog.getNotes());
				messageModel.setAogStatus(aog.getStatus());

				messageModel.setCity(aog.getAirport().getAddress().getCity().getName());
				messageModel.setCountry(aog.getAirport().getAddress().getCity().getCountry().getName());
				messageModel.setVariant(aog.getAircraftVariant().getVariant());
				messageModel.setVariantId(aog.getAircraftVariant().getId());

				messageModels.add(messageModel);
			}

		}
		return messageModels;

	}

}
