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

import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.entity.Country;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.entity.PartCondition;
import com.aerobase.app.model.MessageModel;
import com.aerobase.app.service.AircraftTypeService;
import com.aerobase.app.service.CountryService;
import com.aerobase.app.service.MessageService;
import com.aerobase.app.service.PartCategoryService;
import com.aerobase.app.service.PartConditionService;

@Controller
public class MessageBoardController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private PartCategoryService categoryService;

	@Autowired
	private PartConditionService conditionService;

	@Autowired
	private AircraftTypeService typeService;

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/message-board", method = RequestMethod.GET)
	@Transactional
	public String companyProfile(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		List<MessageModel> messageModels = messageService.findAllModels();

		model.addAttribute("messageModels", messageModels);

		return "main/message-board";
	}

	@RequestMapping(value = "/parts-offered-add", method = RequestMethod.GET)
	@Transactional
	public String formPAOM(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		List<PartCategory> categories = categoryService.getAll();
		List<PartCondition> conditions = conditionService.getAll();
		List<AircraftType> types = typeService.getAll();

		model.addAttribute("categories", categories);
		model.addAttribute("conditions", conditions);
		model.addAttribute("types", types);
		model.addAttribute("postPAOM", new MessageModel());

		return "main/parts-offered-add";
	}

	@RequestMapping(value = "/post-offer-add", method = RequestMethod.POST)
	@Transactional
	public String postPAOM(@Valid @ModelAttribute("postPAOM") MessageModel postPAOM, BindingResult theBindingResult,
			@RequestParam(required = true) String category, @RequestParam(required = true) String condition,
			@RequestParam(required = true) Integer variantId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			List<PartCategory> categories = categoryService.getAll();
			List<PartCondition> conditions = conditionService.getAll();
			List<AircraftType> types = typeService.getAll();

			model.addAttribute("categories", categories);
			model.addAttribute("conditions", conditions);
			model.addAttribute("types", types);

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message posted.\n" + "</div>");

			return "main/parts-offered-add";
		} else {

			if (!condition.equals("0")) {

				postPAOM.setCategory(category);
				postPAOM.setConditionCode(condition);
				postPAOM.setVariantId(variantId);
				messageService.postOfferAdd(postPAOM);

				session.setAttribute("bormes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have posted message <strong>" + "</strong>.\n" + "</div>");

				return "redirect:message-board";
			} else {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Condition must be selected.\n" + "</div>");

				return "redirect:parts-offered-add";
			}
		}

	}

	@RequestMapping(value = "/parts-wanted-add", method = RequestMethod.GET)
	@Transactional
	public String formPAWM(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		List<PartCategory> categories = categoryService.getAll();
		List<PartCondition> conditions = conditionService.getAll();
		List<AircraftType> types = typeService.getAll();
		List<Country> countries = countryService.getAll();

		model.addAttribute("categories", categories);
		model.addAttribute("conditions", conditions);
		model.addAttribute("types", types);
		model.addAttribute("countries", countries);
		model.addAttribute("postPAWM", new MessageModel());

		return "main/parts-wanted-add";
	}

	@RequestMapping(value = "/post-wanted-add", method = RequestMethod.POST)
	@Transactional
	public String postPAWM(@Valid @ModelAttribute("postPAWM") MessageModel postPAWM, BindingResult theBindingResult,
			@RequestParam(required = true) String category, @RequestParam(required = true) String condition,
			@RequestParam(required = true) Integer variantId, @RequestParam(required = true) Integer airportId,
			Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			List<PartCategory> categories = categoryService.getAll();
			List<PartCondition> conditions = conditionService.getAll();
			List<AircraftType> types = typeService.getAll();
			List<Country> countries = countryService.getAll();

			model.addAttribute("categories", categories);
			model.addAttribute("conditions", conditions);
			model.addAttribute("types", types);
			model.addAttribute("countries", countries);

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No message posted.\n" + "</div>");

			return "main/parts-wanted-add";
		} else {

			if (!condition.equals("0") && !category.equals("0")) {
				postPAWM.setCategory(category);
				postPAWM.setConditionCode(condition);
				postPAWM.setVariantId(variantId);
				postPAWM.setAirpotId(airportId);
				messageService.postWantedAdd(postPAWM);

				session.setAttribute("bormes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have posted message <strong>" + "</strong>.\n" + "</div>");

				return "redirect:message-board";
			} else {

				model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Both category and condition must be selected.\n" + "</div>");

				return "redirect:parts-wanted-add";
			}
		}
	}

	// ********

}
