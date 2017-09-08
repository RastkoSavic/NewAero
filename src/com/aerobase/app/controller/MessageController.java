package com.aerobase.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
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

import com.aerobase.app.entity.Message;
import com.aerobase.app.entity.MessageRecipient;
import com.aerobase.app.entity.MessageType;
import com.aerobase.app.entity.User;
import com.aerobase.app.model.ResponseModel;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.service.MessageRecipientService;
import com.aerobase.app.service.MessageService;
import com.aerobase.app.service.MessageTypeService;
import com.aerobase.app.service.UserService;

@Controller
public class MessageController {

	@Autowired
	private MessageTypeService messageTypeService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageRecipientService messageRecipientService;

	@RequestMapping(value = "/admin-messages", method = RequestMethod.GET)
	@Transactional
	public String adminMessages(@ModelAttribute("mes") String mes, Model model) {

		List<Message> messages = messageService.getAll();

		model.addAttribute("messages", messages);

		model.addAttribute("messageAdd", new Message());
		model.addAttribute("messageDelete", new Message());

		return "admin/messages/admin-messages";
	}

	@RequestMapping(value = "/add-admin-message", method = RequestMethod.POST)
	@Transactional
	public String addAdminMessage(@Valid @ModelAttribute("messageAdd") Message messageAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer mssgId,
			@RequestParam(required = true) Integer userId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<Message> messages = messageService.getAll();

			model.addAttribute("messages", messages);

			model.addAttribute("messageDelete", new Message());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message added.\n" + "</div>");

			return "admin/messages/admin-messages";
		} else {

			if (mssgId > 0) {

				Message message = messageService.getById(mssgId);

				message.setMessage(messageAdd.getMessage());

				messageService.update(message);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited the message<strong>" + "</strong>.\n"
						+ "</div>");

				return "redirect:admin-messages";
			} else {

				User user = userService.getById(userId);

				MessageType messageType = messageTypeService.getByCode("ADGM");

				messageAdd.setSender(user);
				messageAdd.setType(messageType);

				messageService.save(messageAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have sent <strong>" + messageAdd.getType().getCode()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-messages";
			}
		}

	}

	@RequestMapping(value = "/admin-question", method = RequestMethod.GET)
	@Transactional
	public String formADQM(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");
		if (userModel != null) {
			model.addAttribute("postADQM", new Message());

			return "main/admin-question";
		} else {

			return "redirect:login";
		}
	}

	@RequestMapping(value = "/send-question", method = RequestMethod.POST)
	@Transactional
	public String postADQM(@Valid @ModelAttribute("postADQM") Message postADQM, BindingResult theBindingResult,
			@RequestParam(required = true) Integer userId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message posted.\n" + "</div>");

			return "main/admin-question";
		} else {

			User user = userService.getById(userId);

			MessageType messageType = messageTypeService.getByCode("ADQM");

			postADQM.setSender(user);
			postADQM.setType(messageType);

			messageService.save(postADQM);

			session.setAttribute("bormes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have posted message <strong>" + "</strong>.\n" + "</div>");

			return "redirect:message-board";
		}
	}

	@RequestMapping(value = "/response", method = RequestMethod.GET)
	@Transactional
	public String formADRM(@ModelAttribute("mes") String mes, @RequestParam Integer reto, @RequestParam Integer onmess,
			Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");
		if (userModel != null) {
			ResponseModel responseModel = new ResponseModel(userModel.getId(), reto, onmess);

			session.setAttribute("responseModel", responseModel);

			model.addAttribute("postADRM", new Message());

			return "main/response";
		} else {

			return "redirect:login";
		}

	}

	@RequestMapping(value = "/response", method = RequestMethod.POST)
	@Transactional
	public String postADRM(@Valid @ModelAttribute("postADRM") Message postADRM, BindingResult theBindingResult,
			@RequestParam(required = true) Integer userId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message posted.\n" + "</div>");

			return "main/response";
		} else {

			ResponseModel responseModel = (ResponseModel) session.getAttribute("responseModel");

			User sender = userService.getById(userId);

			User recipient = userService.getById(responseModel.getRecieverId());

			MessageType messageType = messageTypeService.getByCode("GRMS");

			postADRM.setSender(sender);
			postADRM.setType(messageType);

			int id = messageService.save(postADRM);

			Message message = messageService.getById(id);

			MessageRecipient messageRecipient = new MessageRecipient();
			messageRecipient.setMessage(message);
			messageRecipient.setRecipient(recipient);
			messageRecipientService.save(messageRecipient);

			session.setAttribute("bormes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have posted message <strong>" + "</strong>.\n" + "</div>");

			return "redirect:message-board";
		}
	}

	@RequestMapping(value = "/general-sales-message", method = RequestMethod.GET)
	@Transactional
	public String formGNSM(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		model.addAttribute("postGNSM", new Message());

		return "main/general-sales-message";
	}

	@RequestMapping(value = "/post-sales-add", method = RequestMethod.POST)
	@Transactional
	public String postGNSM(@Valid @ModelAttribute("postGNSM") Message postGNSM, BindingResult theBindingResult,
			@RequestParam(required = true) Integer userId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message posted.\n" + "</div>");

			return "main/general-sales-message";
		} else {

			User user = userService.getById(userId);

			MessageType messageType = messageTypeService.getByCode("GNSM");

			postGNSM.setSender(user);
			postGNSM.setType(messageType);

			messageService.save(postGNSM);

			session.setAttribute("bormes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have posted message <strong>" + "</strong>.\n" + "</div>");

			return "redirect:message-board";
		}

	}

	@RequestMapping(value = "/general-purchase-message", method = RequestMethod.GET)
	@Transactional
	public String formGNPM(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		model.addAttribute("postGNPM", new Message());

		return "main/general-purchase-message";
	}

	@RequestMapping(value = "/post-purchase-add", method = RequestMethod.POST)
	@Transactional
	public String postGNPM(@Valid @ModelAttribute("postGNPM") Message postGNPM, BindingResult theBindingResult,
			@RequestParam(required = true) Integer userId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message posted.\n" + "</div>");

			return "main/general-purchase-message";
		} else {

			User user = userService.getById(userId);

			MessageType messageType = messageTypeService.getByCode("GNPM");

			postGNPM.setSender(user);
			postGNPM.setType(messageType);

			messageService.save(postGNPM);

			session.setAttribute("bormes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have posted message <strong>" + "</strong>.\n" + "</div>");

			return "redirect:message-board";
		}
	}

	@RequestMapping(value = "/delete-message", method = RequestMethod.POST)
	@Transactional
	public String deleteCountry(@ModelAttribute("messageDelete") Message messageDelete, Model model) {

		if (messageService.delete(messageDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> Message deleted <strong>" + "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-messages";
	}
}
