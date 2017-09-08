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

import com.aerobase.app.entity.AircraftModel;
import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.service.AircraftModelService;
import com.aerobase.app.service.AircraftTypeService;

@Controller
public class AircraftModelController {

	@Autowired
	private AircraftTypeService aircraftTypeService;

	@Autowired
	private AircraftModelService aircraftModelService;

	@RequestMapping(value = "/admin-aircraft-model", method = RequestMethod.GET)
	@Transactional
	public String adminAircraftModels(@ModelAttribute("mes") String mes, Model model) {

		List<AircraftModel> aircraftModels = aircraftModelService.getAll();
		List<AircraftType> aircraftTypes = aircraftTypeService.getAll();

		model.addAttribute("models", aircraftModels);
		model.addAttribute("types", aircraftTypes);

		model.addAttribute("aircraftModelAdd", new AircraftModel());
		model.addAttribute("aircraftModelDelete", new AircraftModel());

		return "admin/aircrafts/admin-aircraft-model";
	}

	@RequestMapping(value = "/add-aircraft-model", method = RequestMethod.POST)
	@Transactional
	public String addAircraftModel(@Valid @ModelAttribute("aircraftModelAdd") AircraftModel aircraftModelAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer typeId,
			@RequestParam(required = false) Integer modId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<AircraftModel> aircraftModels = aircraftModelService.getAll();
			List<AircraftType> aircraftTypes = aircraftTypeService.getAll();

			model.addAttribute("models", aircraftModels);
			model.addAttribute("types", aircraftTypes);

			model.addAttribute("aircraftModelDelete", new AircraftModel());

			return "admin/aircrafts/admin-aircraft-model";
		} else {

			if (modId > 0) {

				AircraftModel aircraftModel = aircraftModelService.getById(modId);
				aircraftModel.setModel(aircraftModelAdd.getModel());
				aircraftModelService.update(aircraftModel);
				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + aircraftModelAdd.getModel()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-aircraft-model";
			} else {

				AircraftType aircraftType = aircraftTypeService.getById(typeId);

				aircraftModelAdd.setType(aircraftType);

				aircraftModelService.save(aircraftModelAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + aircraftModelAdd.getModel()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-aircraft-model";
			}

		}
	}

	@RequestMapping(value = "/delete-aircraft-model", method = RequestMethod.POST)
	public String deleteModel(@ModelAttribute("aircraftModelDelete") AircraftModel aircraftModelDelete, Model model) {

		if (aircraftModelService.delete(aircraftModelDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + aircraftModelDelete.getModel()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-aircraft-model";
	}
}
