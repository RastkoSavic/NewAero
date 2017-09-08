package com.aerobase.app.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aerobase.app.entity.Address;
import com.aerobase.app.entity.Company;
import com.aerobase.app.entity.CompanySettings;
import com.aerobase.app.entity.StockPart;
import com.aerobase.app.entity.User;
import com.aerobase.app.entity.UserSettings;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.service.CompanyService;
import com.aerobase.app.service.UserService;

@Controller
public class ViewProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/view-company-profile", method = RequestMethod.GET)
	@Transactional
	private String showCompany(@ModelAttribute("mes") String mes, @RequestParam Integer usproid,
			@RequestParam Integer onmess, Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");
		if (userModel != null) {
			Company company = companyService.getById(usproid);

			Set<Address> addresses = company.getAddresses();
			for (Address address : addresses) {
				Hibernate.initialize(address);
			}

			Set<User> users = company.getUsers();

			CompanySettings companySettings = company.getSettings();

			Hibernate.initialize(company.getStock().getStockParts());

			Set<StockPart> stockParts = company.getStock().getStockParts();

			for (StockPart stockPart : stockParts) {
				Hibernate.initialize(stockPart.getPart());
			}

			model.addAttribute("company", company);
			model.addAttribute("users", users);
			model.addAttribute("companySettings", companySettings);
			model.addAttribute("stockParts", stockParts);

			model.addAttribute("addresses", addresses);

			return "main/view-company-profile";
		} else {

			return "redirect:login";
		}

	}

	@RequestMapping(value = "/view-user-profile", method = RequestMethod.GET)
	@Transactional
	private String showUser(@ModelAttribute("mes") String mes, @RequestParam Integer usproid,
			@RequestParam Integer onmess, Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");
		if (userModel != null) {
			User user = userService.getById(usproid);

			Company company = user.getCompany();

			CompanySettings companySettings = company.getSettings();

			UserSettings userSettings = user.getSettings();

			model.addAttribute("company", company);
			model.addAttribute("companySettings", companySettings);
			model.addAttribute("user", user);
			model.addAttribute("userSettings", userSettings);

			return "main/view-user-profile";
		} else {

			return "redirect:login";
		}

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
