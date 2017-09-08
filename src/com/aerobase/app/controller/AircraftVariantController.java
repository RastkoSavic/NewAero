package com.aerobase.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aerobase.app.entity.AircraftModel;
import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.entity.AircraftVariant;
import com.aerobase.app.service.AircraftModelService;
import com.aerobase.app.service.AircraftTypeService;
import com.aerobase.app.service.AircraftVariantService;
import com.google.gson.Gson;

@Controller
public class AircraftVariantController {

	@Autowired
	private AircraftTypeService aircraftTypeService;

	@Autowired
	private AircraftModelService aircraftModelService;

	@Autowired
	private AircraftVariantService aircraftVariantService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping(value = "/admin-aircraft-variant", method = RequestMethod.GET)
	@Transactional
	public String adminAircraftVariants(@ModelAttribute("mes") String mes, Model model) {

		List<AircraftVariant> aircraftVariants = aircraftVariantService.getAll();
		List<AircraftType> aircraftTypes = aircraftTypeService.getAll();

		model.addAttribute("variants", aircraftVariants);
		model.addAttribute("types", aircraftTypes);

		model.addAttribute("aircraftVariantAdd", new AircraftVariant());
		model.addAttribute("aircraftVariantDelete", new AircraftVariant());

		return "admin/aircrafts/admin-aircraft-variant";
	}

	@RequestMapping(value = "/select-type", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody String selectType(@RequestParam(required = true) String object, Model model) {

		if (Integer.parseInt(object) == 0) {

			return null;
		} else {

			Gson gson = new Gson();

			return gson.toJson(aircraftModelService.listForModel(Integer.parseInt(object)));
		}

	}

	@RequestMapping(value = "/select-model", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody String selectModel(@RequestParam(required = true) Integer object, Model model) {

		if (object <= 0) {

			return null;
		} else {

			Gson gson = new Gson();

			return gson.toJson(aircraftVariantService.listForModel(object));
		}

	}

	@RequestMapping(value = "/add-aircraft-variant", method = RequestMethod.POST)
	@Transactional
	public String addAircraftVariant(@Valid @ModelAttribute("aircraftVariantAdd") AircraftVariant aircraftVariantAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer modelId,
			@RequestParam(required = false) Integer varId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<AircraftVariant> aircraftVariants = aircraftVariantService.getAll();
			List<AircraftType> aircraftTypes = aircraftTypeService.getAll();

			model.addAttribute("variants", aircraftVariants);
			model.addAttribute("types", aircraftTypes);

			model.addAttribute("aircraftVariantDelete", new AircraftVariant());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No variant added or changed.\n" + "</div>");

			return "admin/aircrafts/admin-aircraft-variant";
		} else {

			if (varId > 0) {

				AircraftVariant variant = aircraftVariantService.getById(varId);
				variant.setVariant(aircraftVariantAdd.getVariant());
				aircraftVariantService.update(variant);
				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + aircraftVariantAdd.getVariant()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-aircraft-variant";
			} else {

				if (modelId > 0) {
					
					AircraftModel aircraftModel = aircraftModelService.getById(modelId);

					aircraftVariantAdd.setModel(aircraftModel);

					aircraftVariantService.save(aircraftVariantAdd);

					model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success!</strong> You have added <strong>" + aircraftVariantAdd.getVariant()
							+ "</strong>.\n" + "</div>");
				} else {
					
					model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Wrong input!</strong> Model must be selected.\n" + "</div>");
				}
				

				return "redirect:admin-aircraft-variant";
			}

		}
	}

	@RequestMapping(value = "/delete-aircraft-variant", method = RequestMethod.POST)
	public String deleteVariantType(@ModelAttribute("aircraftVariantDelete") AircraftVariant aircraftVariantDelete,
			Model model) {

		if (aircraftVariantService.delete(aircraftVariantDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + aircraftVariantDelete.getVariant()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-aircraft-variant";
	}
}
