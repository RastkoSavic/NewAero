package com.aerobase.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.service.AircraftTypeService;
//import com.google.gson.Gson;

@Controller
public class AircraftTypeController {

	@Autowired
	private AircraftTypeService aircraftTypeService;

	@RequestMapping(value = "/admin-aircraft-type", method = RequestMethod.GET)
	public String adminAircraftTypes(@ModelAttribute("mes") String mes, Model model) {

		List<AircraftType> aircraftTypes = aircraftTypeService.getAll();

		model.addAttribute("types", aircraftTypes);

		model.addAttribute("aircraftTypeAdd", new AircraftType());
		model.addAttribute("aircraftTypeEdit", new AircraftType());
		model.addAttribute("aircraftTypeDelete", new AircraftType());

		return "admin/aircrafts/admin-aircraft-type";
	}

	@RequestMapping(value = "/add-aircraft-type", method = RequestMethod.POST)
	@Transactional
	public String addAircraftType(@Valid @ModelAttribute("aircraftTypeAdd") AircraftType aircraftTypeAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer typeId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<AircraftType> aircraftTypes = aircraftTypeService.getAll();

			model.addAttribute("types", aircraftTypes);

			model.addAttribute("aircraftTypeDelete", new AircraftType());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No type added or changed.\n" + "</div>");

			return "admin/aircrafts/admin-aircraft-type";
		} else {

			if (typeId > 0) {

				AircraftType type = aircraftTypeService.getById(typeId);
				type.setType(aircraftTypeAdd.getType());
				aircraftTypeService.update(type);
				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + aircraftTypeAdd.getType()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-aircraft-type";
			} else {

				aircraftTypeService.save(aircraftTypeAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + aircraftTypeAdd.getType()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-aircraft-type";
			}
		}

	}

	/*
	 * @RequestMapping(value = "/get-aircraft-type", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String
	 * getAircraftType(@RequestParam(required = true) String action,
	 * 
	 * @RequestParam(required = true) Integer object, Model model) {
	 * 
	 * AircraftType aircraftType = aircraftTypeService.getById(object);
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson(new String[] { String.valueOf(aircraftType.getId()),
	 * aircraftType.getType() }); }
	 */

	/*
	 * @RequestMapping(value = "/edit-aircraft-type", method = RequestMethod.POST)
	 * public String editAircraftType(@ModelAttribute("aircraftTypeEdit")
	 * AircraftType aircraftTypeEdit, Model model) {
	 * 
	 * if (aircraftTypeEdit.getType() != null &&
	 * !aircraftTypeEdit.getType().isEmpty()) {
	 * 
	 * aircraftTypeService.update(aircraftTypeEdit);
	 * 
	 * model.addAttribute("mes",
	 * "<div class=\"alert alert-success alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>Success.</strong> You have edited <strong>" +
	 * aircraftTypeEdit.getType() + "</strong>.\n" + "</div>"); } else {
	 * 
	 * model.addAttribute("message",
	 * "<div class=\"alert alert-danger alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>No input.</strong> No changes made.\n" + "</div>"); }
	 * 
	 * return "redirect:admin-aircraft-type"; }
	 */

	@RequestMapping(value = "/delete-aircraft-type", method = RequestMethod.POST)
	public String deleteAircraftType(@ModelAttribute("aircraftTypeDelete") AircraftType aircraftTypeDelete,
			Model model) {

		if (aircraftTypeService.delete(aircraftTypeDelete)) {
			
			model.addAttribute("mes",
					"<div class=\"alert alert-warning alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success.</strong> You have deleted <strong>" + aircraftTypeDelete.getType()
							+ "</strong>.\n" + "</div>");
		} else {
			
			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		

		return "redirect:admin-aircraft-type";
	}
}
