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
import org.springframework.web.bind.annotation.ResponseBody;

import com.aerobase.app.entity.Address;
import com.aerobase.app.entity.Airport;
import com.aerobase.app.entity.City;
import com.aerobase.app.entity.Country;
import com.aerobase.app.model.AirportModel;
import com.aerobase.app.service.AddressService;
import com.aerobase.app.service.AirportService;
import com.aerobase.app.service.CityService;
import com.aerobase.app.service.CountryService;
import com.google.gson.Gson;

@Controller
public class AirportController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AirportService airportService;

	@RequestMapping(value = "/admin-airports", method = RequestMethod.GET)
	@Transactional
	public String adminAirports(@ModelAttribute("mes") String mes, Model model) {

		List<Airport> airports = airportService.getAll();
		List<Country> countries = countryService.getAll();

		model.addAttribute("airports", airports);
		model.addAttribute("countries", countries);

		model.addAttribute("airportAdd", new AirportModel());

		return "admin/locations/admin-airports";
	}

	@RequestMapping(value = "/select-country-for-airport", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody String selectCountry(@RequestParam(required = true) String object, Model model) {

		if (object.equals("")) {

			return null;
		} else {

			Gson gson = new Gson();

			return gson.toJson(cityService.listForModel(object));
		}

	}

	@RequestMapping(value = "/select-city-for-airport", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody String selectCity(@RequestParam(required = true) Integer obid, Model model) {

		if (obid < 0) {

			return null;
		} else {

			Gson gson = new Gson();

			return gson.toJson(airportService.listForModel(obid));
		}

	}

	@RequestMapping(value = "/add-airport", method = RequestMethod.POST)
	@Transactional
	public String addAirport(@Valid @ModelAttribute("airportAdd") AirportModel airportAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer cityId,
			@RequestParam(required = false) Integer airpId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<Airport> airports = airportService.getAll();
			List<Country> countries = countryService.getAll();

			model.addAttribute("airports", airports);
			model.addAttribute("countries", countries);

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No airport added or changed.\n" + "</div>");

			return "admin/locations/admin-airports";
		} else {

			if (airpId > 0) {

				Airport airport = airportService.getById(airpId);

				Address address = airport.getAddress();

				address.setAddress(airportAdd.getAddress());

				addressService.update(address);

				airport.setName(airportAdd.getName());

				airport.setIATACode(airportAdd.getIATACode());

				airportService.update(airport);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + airportAdd.getName() + "</strong>.\n"
						+ "</div>");

				return "redirect:admin-airports";
			} else {

				if (cityId > 0) {

					City city = cityService.getById(cityId);
					Address addressNew = new Address(airportAdd.getAddress(), "Airport", "Hub");

					addressNew.setCity(city);
					addressService.save(addressNew);

					Airport airport = new Airport(airportAdd.getName());
					airport.setIATACode(airportAdd.getIATACode());
					airport.setAddress(addressNew);
					airportService.save(airport);

					model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success!</strong> You have added <strong>" + airportAdd.getName()
							+ "</strong>.\n" + "</div>");

					return "redirect:admin-airports";

				} else {

					model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Wrong input!</strong> City must be selected.\n" + "</div>");
					return "redirect:admin-airports";
				}
			}
		}

	}

	@RequestMapping(value = "/delete-airport", method = RequestMethod.POST)
	@Transactional
	public String deleteAirport(@RequestParam(required = true) Integer aiId, Model model) {

		Airport airport = airportService.getById(aiId);

		if (airportService.delete(airport)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + airport.getName() + "</strong>.\n"
					+ "</div>");

		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-airports";
	}

}
