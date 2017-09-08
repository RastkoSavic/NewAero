package com.aerobase.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.aerobase.app.entity.MessageType;
import com.aerobase.app.service.MessageTypeService;
//import com.google.gson.Gson;

@Controller
public class MessageTypeController {

	@Autowired
	private MessageTypeService messageTypeService;

	@RequestMapping(value = "/admin-message-types", method = RequestMethod.GET)
	public String adminMessageTypes(@ModelAttribute("mes") String mes, Model model) {

		List<MessageType> messageTypes = messageTypeService.getAll();

		model.addAttribute("types", messageTypes);

		model.addAttribute("messageTypeAdd", new MessageType());
		model.addAttribute("messageTypeDelete", new MessageType());

		return "admin/messages/admin-message-types";
	}

	@RequestMapping(value = "/add-message-type", method = RequestMethod.POST)
	@Transactional
	public String addMessageType(@Valid @ModelAttribute("messageTypeAdd") MessageType messageTypeAdd,
			BindingResult theBindingResult, @RequestParam(required = false) String mstyId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<MessageType> messageTypes = messageTypeService.getAll();

			model.addAttribute("types", messageTypes);

			model.addAttribute("messageTypeDelete", new MessageType());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No type added or changed.\n" + "</div>");

			return "admin/messages/admin-message-types";
		} else {

			if (!mstyId.isEmpty()) {

				MessageType messageType = messageTypeService.getByCode(mstyId);

				messageType.setType(messageTypeAdd.getType());
				messageType.setDescription(messageTypeAdd.getDescription());

				messageTypeService.update(messageType);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + messageTypeAdd.getType()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-message-types";

			} else {

				messageTypeService.save(messageTypeAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + messageTypeAdd.getType()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-message-types";
			}
		}

	}

	/*
	 * @RequestMapping(value = "/get-message-type", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String
	 * getMessageType(@RequestParam(required = true) String action,
	 * 
	 * @RequestParam(required = true) String object, Model model) {
	 * 
	 * MessageType messageType = messageTypeService.getByCode(object);
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson(new String[] { messageType.getCode(),
	 * messageType.getType(), messageType.getDescription() }); }
	 * 
	 * @RequestMapping(value = "/edit-message-type", method = RequestMethod.POST)
	 * public String editMessageType(@ModelAttribute("messageTypeEdit") MessageType
	 * messageTypeEdit, Model model) {
	 * 
	 * if (messageTypeEdit.getType() != null &&
	 * !messageTypeEdit.getType().isEmpty()) {
	 * 
	 * messageTypeService.update(messageTypeEdit);
	 * 
	 * model.addAttribute("mes",
	 * "<div class=\"alert alert-success alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>Success.</strong> You have edited <strong>" +
	 * messageTypeEdit.getType() + "</strong>.\n" + "</div>"); } else {
	 * 
	 * model.addAttribute("message",
	 * "<div class=\"alert alert-danger alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>No input.</strong> No changes made.\n" + "</div>"); }
	 * 
	 * return "redirect:admin-message-types"; }
	 */

	@RequestMapping(value = "/delete-message-type", method = RequestMethod.POST)
	public String deleteMessageType(@ModelAttribute("messageTypeDelete") MessageType messageTypeDelete, Model model) {

		if (messageTypeService.delete(messageTypeDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + messageTypeDelete.getType()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-message-types";
	}
}
