package com.aerobase.app.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Hibernate;
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

import com.aerobase.app.entity.Address;
import com.aerobase.app.entity.City;
import com.aerobase.app.entity.Company;
import com.aerobase.app.entity.Country;
import com.aerobase.app.entity.User;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.service.AddressService;
import com.aerobase.app.service.CityService;
import com.aerobase.app.service.CountryService;
import com.aerobase.app.service.UserService;
//import com.google.gson.Gson;

@Controller
public class CompanyAddresses {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/company-addresses", method = RequestMethod.GET)
	@Transactional
	public String adminAddresses(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");

		User user = userService.getById(userModel.getId());

		Company company = user.getCompany();

		Set<Address> addresses = company.getAddresses();
		for (Address address : addresses) {
			Hibernate.initialize(address);
		}
		List<Country> countries = countryService.getAll();

		model.addAttribute("addresses", addresses);
		model.addAttribute("countries", countries);

		model.addAttribute("addressAdd", new Address());
		model.addAttribute("addressDelete", new Address());

		return "main/company-addresses";
	}

	/*
	 * @RequestMapping(value = "/select-country", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String
	 * selectCountry(@RequestParam(required = true) String object, Model model) {
	 * 
	 * if (object.equals("")) {
	 * 
	 * return null; } else {
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson(cityService.listForModel(object)); }
	 * 
	 * }
	 */

	@RequestMapping(value = "/add-company-address", method = RequestMethod.POST)
	@Transactional
	public String addAddress(@Valid @ModelAttribute("addressAdd") Address addressAdd, BindingResult theBindingResult,
			@RequestParam(required = false) Integer cityId, @RequestParam(required = false) Integer addrId, Model model,
			HttpSession session) {

		if (theBindingResult.hasErrors()) {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			User user = userService.getById(userModel.getId());

			Company company = user.getCompany();

			Set<Address> addresses = company.getAddresses();
			for (Address address : addresses) {
				Hibernate.initialize(address);
			}
			List<Country> countries = countryService.getAll();

			model.addAttribute("addresses", addresses);
			model.addAttribute("countries", countries);
			model.addAttribute("addressDelete", new Address());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No address added or changed.\n" + "</div>");

			return "main/company-addresses";
		} else {

			if (addrId > 0) {

				Address address = addressService.getById(addrId);

				address.setAddress(addressAdd.getAddress());
				address.setType(addressAdd.getType());
				address.setUsedFor(addressAdd.getUsedFor());

				addressService.update(address);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + addressAdd.getAddress()
						+ "</strong>.\n" + "</div>");

				return "redirect:company-addresses";
			} else {

				if (cityId > 0) {

					UserModel userModel = (UserModel) session.getAttribute("currentUser");

					User user = userService.getById(userModel.getId());

					Company company = user.getCompany();

					City city = cityService.getById(cityId);

					addressAdd.setCity(city);

					addressAdd.getCompanies().add(company);
					company.getAddresses().add(addressAdd);

					addressService.save(addressAdd);

					model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success!</strong> You have added <strong>" + addressAdd.getAddress()
							+ "</strong>.\n" + "</div>");

					return "redirect:company-addresses";
				} else {

					model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Wrong input!</strong> City must be selected.\n" + "</div>");

					return "redirect:company-addresses";
				}
			}
		}
	}

	@RequestMapping(value = "/delete-company-address", method = RequestMethod.POST)
	public String deleteModel(@ModelAttribute("addressDelete") Address addressDelete, Model model) {

		if (addressService.delete(addressDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + addressDelete.getAddress()
					+ "</strong>.\n" + "</div>");

		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:company-addresses";
	}
}
