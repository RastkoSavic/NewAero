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

import com.aerobase.app.entity.Country;
import com.aerobase.app.service.CountryService;
//import com.google.gson.Gson;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/admin-countries", method = RequestMethod.GET)
	public String adminCountries(@ModelAttribute("mes") String mes, Model model) {

		List<Country> countries = countryService.getAll();

		model.addAttribute("countries", countries);

		model.addAttribute("countryAdd", new Country());
		model.addAttribute("countryDelete", new Country());

		return "admin/locations/admin-countries";
	}

	@RequestMapping(value = "/add-country", method = RequestMethod.POST)
	@Transactional
	public String addCountry(@Valid @ModelAttribute("countryAdd") Country countryAdd, BindingResult theBindingResult,
			@RequestParam(required = false) String cntrId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<Country> countries = countryService.getAll();

			model.addAttribute("countries", countries);

			model.addAttribute("countryDelete", new Country());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No country added or changed.\n" + "</div>");

			return "admin/locations/admin-countries";
		} else {

			if (!cntrId.isEmpty()) {

				Country country = countryService.getByCode(cntrId);

				country.setName(countryAdd.getName());

				countryService.update(country);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + countryAdd.getName() + "</strong>.\n"
						+ "</div>");

				return "redirect:admin-countries";
			} else {

				countryService.save(countryAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + countryAdd.getName() + "</strong>.\n"
						+ "</div>");

				return "redirect:admin-countries";
			}
		}

	}

	/*
	 * @RequestMapping(value = "/get-country", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String getCountry(@RequestParam(required
	 * = true) String action,
	 * 
	 * @RequestParam(required = true) String object, Model model) {
	 * 
	 * Country country = countryService.getByCode(object);
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson(new String[] { country.getCode(), country.getName() }); }
	 * 
	 * @RequestMapping(value = "/edit-country", method = RequestMethod.POST) public
	 * String editCountry(@ModelAttribute("countryEdit") Country countryEdit, Model
	 * model) {
	 * 
	 * if (countryEdit.getName() != null && !countryEdit.getName().isEmpty()) {
	 * 
	 * countryService.update(countryEdit);
	 * 
	 * model.addAttribute("mes",
	 * "<div class=\"alert alert-success alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>Success.</strong> You have edited <strong>" +
	 * countryEdit.getName() + "</strong>.\n" + "</div>"); } else {
	 * 
	 * model.addAttribute("message",
	 * "<div class=\"alert alert-danger alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>No input.</strong> No changes made.\n" + "</div>"); }
	 * 
	 * return "redirect:admin-countries"; }
	 */

	@RequestMapping(value = "/delete-country", method = RequestMethod.POST)
	@Transactional
	public String deleteCountry(@ModelAttribute("countryDelete") Country countryDelete, Model model) {

		if (countryService.delete(countryDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + countryDelete.getName() + "</strong>.\n"
					+ "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-countries";
	}
}
