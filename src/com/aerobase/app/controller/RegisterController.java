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
import com.aerobase.app.entity.CompanySettings;
import com.aerobase.app.entity.CompanyStock;
import com.aerobase.app.entity.PrimaryService;
import com.aerobase.app.entity.User;
import com.aerobase.app.entity.UserSettings;
import com.aerobase.app.model.LoginModel;
import com.aerobase.app.model.RegisterModel;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.service.CompanyService;
import com.aerobase.app.service.CompanySettingsService;
import com.aerobase.app.service.CompanyStockService;
import com.aerobase.app.service.PrimaryServiceService;
import com.aerobase.app.service.UserService;
import com.aerobase.app.service.UserSettingsService;
import com.aerobase.app.service.UserStatusService;

@Controller
public class RegisterController {

	@Autowired
	private PrimaryServiceService primaryServiceService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanySettingsService companySettingsService;

	@Autowired
	private CompanyStockService companyStockService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserSettingsService userSettingsService;

	@Autowired
	private UserStatusService userStatusService;

	@RequestMapping(value = "/register-company", method = RequestMethod.GET)
	@Transactional
	public String registerForm(@ModelAttribute("mes") String mes, Model model) {

		List<PrimaryService> primaryServices = primaryServiceService.getAll();

		model.addAttribute("services", primaryServices);

		model.addAttribute("companyAdd", new RegisterModel());

		return "register-company";
	}

	@RequestMapping(value = "/register-company", method = RequestMethod.POST)
	@Transactional
	public String addCompany(@Valid @ModelAttribute("companyAdd") RegisterModel companyAdd,
			BindingResult theBindingResult, @RequestParam(required = true) Integer serviceId, Model model,
			HttpSession session) {

		if (theBindingResult.hasErrors()) {

			List<PrimaryService> primaryServices = primaryServiceService.getAll();

			model.addAttribute("services", primaryServices);

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No company added.\n" + "</div>");

			return "register-company";
		} else {

			User userCheck = userService.getByEmail(companyAdd.getCompanyEmail());
			
			if (userCheck != null) {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Email already exists.\n" + "</div>");

				return "redirect:register-company";
			} else if (!companyAdd.getMasterPass().equals(companyAdd.getRepeatPass())) {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Password does not match the confirmation.\n" + "</div>");

				return "redirect:register-company";
			} else {

				PrimaryService primaryService = primaryServiceService.getById(serviceId);

				Company company = new Company(companyAdd.getName(), companyAdd.getCompanyEmail(),
						companyAdd.getMasterPass());
				company.setPrimaryService(primaryService);

				CompanySettings companySettings = new CompanySettings(company, (byte) 0, (byte) 1, (byte) 1, (byte) 1,
						(byte) 1);
				CompanyStock companyStock = new CompanyStock(company);

				User user = new User(companyAdd.getFirstName(), companyAdd.getLastName(), companyAdd.getCompanyEmail(),
						companyAdd.getMasterPass());
				user.setCompany(company);
				user.setStatus(userStatusService.getByStatus("SuperUser"));

				UserSettings userSettings = new UserSettings(user, (byte) 1, (byte) 1, (byte) 1, (byte) 1);

				companyService.save(company);

				int id = userService.save(user);

				companySettingsService.save(companySettings);

				companyStockService.save(companyStock);

				userSettingsService.save(userSettings);

				session.setAttribute("currentUser", new UserModel(id, "SuperUser", company.getId()));

				session.setAttribute("regmes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + companyAdd.getName() + "</strong>.\n"
						+ "</div>");

				return "index";
			}
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@Transactional
	public String loginForm(@ModelAttribute("mes") String mes, Model model) {

		model.addAttribute("userSet", new LoginModel());

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional
	public String setUser(@Valid @ModelAttribute("userSet") LoginModel userSet, BindingResult theBindingResult, Model model,
			HttpSession session) {

		if (theBindingResult.hasErrors()) {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> Try again.\n" + "</div>");

			return "login";
		} else {
			UserModel userModel = userService.login(userSet);

			if (userModel != null) {

				session.setAttribute("currentUser", userModel);

				session.setAttribute("regmes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You are logged in <strong>" + "</strong>.\n" + "</div>");
				return "index";
			} else {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Email and/or password wrong.\n" + "</div>");

				return "login";
			}

		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@Transactional
	public String logout(Model model, HttpSession session) {
		
		session.setAttribute("currentUser", null);
		
		return "index";
	}
}
