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

import com.aerobase.app.entity.Company;
import com.aerobase.app.entity.Message;
import com.aerobase.app.entity.User;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.model.UserSettingsModel;
import com.aerobase.app.service.MessageRecipientService;
import com.aerobase.app.service.UserService;

@Controller
public class UserProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageRecipientService messageRecipientService;

	@RequestMapping(value = "/user-profile", method = RequestMethod.GET)
	@Transactional
	public String userProfile(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");
		User user = userService.getById(userModel.getId());
		Company company = user.getCompany();
		UserSettingsModel userSettings = userService.forSettingsModel(user.getId());
		List<Message> messages = messageRecipientService.getMessages(user.getId());
		for (Message message : messages) {
			System.out.println(message.getMessage());
		}
		model.addAttribute("userSettings", userSettings);
		model.addAttribute("user", user);
		model.addAttribute("company", company);
		model.addAttribute("messages", messages);

		return "main/user-profile";
	}

	@RequestMapping(value = "/user-sett", method = RequestMethod.POST)
	@Transactional
	public String userSetting(@Valid @ModelAttribute("userSettings") UserSettingsModel userSettings,
			BindingResult theBindingResult, @RequestParam(required = true) Integer usId, Model model,
			HttpSession session) {

		if (theBindingResult.hasErrors()) {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			User user = userService.getById(userModel.getId());
			Company company = user.getCompany();

			model.addAttribute("user", user);
			model.addAttribute("company", company);

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No changes made.\n" + "</div>");

			return "main/user-profile";
		} else {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			userService.setAll(userSettings, userModel.getId());

			model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have changed settings <strong>" + "</strong>.\n" + "</div>");

			return "redirect:user-profile";
		}

	}

}
