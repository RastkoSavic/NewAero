package com.aerobase.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aerobase.app.model.MessageModel;
import com.aerobase.app.service.MessageService;

@Controller
public class AOGController {
	
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/admin-aog", method = RequestMethod.GET)
	@Transactional
	public String adminAog(Model model, HttpSession session) {

		List<MessageModel> messageModels = messageService.findAOGModels();

		model.addAttribute("messageModels", messageModels);

		return "admin/aircrafts/admin-aog";
	}
}
