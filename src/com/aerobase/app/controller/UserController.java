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
import com.aerobase.app.entity.User;
import com.aerobase.app.entity.UserSettings;
import com.aerobase.app.model.RegisterAdminModel;
import com.aerobase.app.service.CompanyService;
import com.aerobase.app.service.UserService;
import com.aerobase.app.service.UserSettingsService;
import com.aerobase.app.service.UserStatusService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserStatusService userStatusService;

	@Autowired
	private UserSettingsService userSettingsService;

	@RequestMapping(value = "/admin-users", method = RequestMethod.GET)
	@Transactional
	public String adminUsers(@ModelAttribute("mes") String mes, Model model) {

		List<User> users = userService.getAll();

		model.addAttribute("users", users);

		model.addAttribute("adminAdd", new RegisterAdminModel());

		return "admin/companies/admin-users";
	}

	@RequestMapping(value = "/register-admin", method = RequestMethod.POST)
	@Transactional
	public String addAdmin(@Valid @ModelAttribute("adminAdd") RegisterAdminModel adminAdd,
			BindingResult theBindingResult, @RequestParam(required = true) String status, Model model,
			HttpSession session) {

		if (theBindingResult.hasErrors()) {

			List<User> users = userService.getAll();

			model.addAttribute("users", users);

			User admin = userService.getByEmail(adminAdd.getEmail());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No admin added.\n" + "</div>");

			if (admin != null) {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Email already exists.\n" + "</div>");
			}

			if (!adminAdd.getPassword().equals(adminAdd.getRepeatPass())) {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Password does not match the confirmation.\n" + "</div>");
			}

			return "admin/companies/admin-users";
		} else {

			User admin = userService.getByEmail(adminAdd.getEmail());

			if (admin != null) {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Email already exists.\n" + "</div>");
			} else if (!adminAdd.getPassword().equals(adminAdd.getRepeatPass())) {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Password does not match the confirmation.\n" + "</div>");
			} else {

				Company company = companyService.getById(1);

				User user = new User(adminAdd.getFirstName(), adminAdd.getLastName(), adminAdd.getEmail(),
						adminAdd.getPassword());

				UserSettings userSettings = new UserSettings(user, (byte) 1, (byte) 1, (byte) 1, (byte) 1);

				user.setCompany(company);

				user.setStatus(userStatusService.getByStatus(status));

				userService.save(user);

				userSettingsService.save(userSettings);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + adminAdd.getFirstName() + " "
						+ adminAdd.getLastName() + "</strong>.\n" + "</div>");

			}
			return "redirect:admin-users";
		}

	}

	@RequestMapping(value = "/delete-admin", method = RequestMethod.POST)
	@Transactional
	public String deleteAdmin(@RequestParam(required = true) Integer adminId, Model model) {

		User admin = userService.getById(adminId);

		if (userService.delete(admin)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + admin.getFirstName() + " "
					+ admin.getLastName() + "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-users";
	}
}
