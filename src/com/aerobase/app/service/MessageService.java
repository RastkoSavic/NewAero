package com.aerobase.app.service;

import java.util.List;

import com.aerobase.app.entity.Message;
import com.aerobase.app.model.MessageModel;

public interface MessageService {

	public int save(Message message);

	public List<Message> getAll();

	public Message getById(int id);

	public void update(Message message);

	public boolean delete(Message message);

	public List<MessageModel> findAllModels();

	public void postAdd(MessageModel postGNSM);

	public void postOfferAdd(MessageModel postPAOM);

	public void postWantedAdd(MessageModel postPAWM);

	public List<MessageModel> findAOGModels();

}
