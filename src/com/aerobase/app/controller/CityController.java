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

import com.aerobase.app.entity.City;
import com.aerobase.app.entity.Country;
import com.aerobase.app.service.CityService;
import com.aerobase.app.service.CountryService;

@Controller
public class CityController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/admin-cities", method = RequestMethod.GET)
	@Transactional
	public String adminCities(@ModelAttribute("mes") String mes, Model model) {

		List<City> cities = cityService.getAll();
		List<Country> countries = countryService.getAll();

		model.addAttribute("cities", cities);
		model.addAttribute("countries", countries);

		model.addAttribute("cityAdd", new City());
		model.addAttribute("cityDelete", new City());

		return "admin/locations/admin-cities";
	}

	@RequestMapping(value = "/add-city", method = RequestMethod.POST)
	@Transactional
	public String addCity(@Valid @ModelAttribute("cityAdd") City cityAdd, BindingResult theBindingResult,
			@RequestParam(required = false) Integer citId, @RequestParam(required = false) String countryCode,
			Model model) {

		if (theBindingResult.hasErrors()) {

			List<City> cities = cityService.getAll();
			List<Country> countries = countryService.getAll();

			model.addAttribute("cities", cities);
			model.addAttribute("countries", countries);

			model.addAttribute("cityDelete", new City());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No city added.\n" + "</div>");

			return "admin/locations/admin-cities";
		} else {

			if (citId > 0) {

				City city = cityService.getById(citId);

				city.setName(cityAdd.getName());
				city.setStateCode(cityAdd.getStateCode());
				city.setStateName(cityAdd.getStateName());

				cityService.update(city);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + cityAdd.getName() + "</strong>.\n"
						+ "</div>");

				return "redirect:admin-cities";
			} else {

				if (!countryCode.isEmpty()) {

					Country country = countryService.getByCode(countryCode);
					cityAdd.setCountry(country);

					cityService.save(cityAdd);

					model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success!</strong> You have added <strong>" + cityAdd.getName() + "</strong>.\n"
							+ "</div>");

					return "redirect:admin-cities";
				} else {

					model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Wrong input!</strong> Country must be selected.\n" + "</div>");

					return "redirect:admin-cities";
				}
			}
		}

	}

	@RequestMapping(value = "/delete-city", method = RequestMethod.POST)
	public String deleteModel(@ModelAttribute("cityDelete") City cityDelete, Model model) {

		if (cityService.delete(cityDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + cityDelete.getName() + "</strong>.\n"
					+ "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-cities";
	}
}
