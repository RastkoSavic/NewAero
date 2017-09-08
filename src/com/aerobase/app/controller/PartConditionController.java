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

import com.aerobase.app.entity.PartCondition;
import com.aerobase.app.service.PartConditionService;
//import com.google.gson.Gson;

@Controller
public class PartConditionController {

	@Autowired
	private PartConditionService partConditionService;

	@RequestMapping(value = "/admin-part-conditions", method = RequestMethod.GET)
	public String adminPartConditions(@ModelAttribute("mes") String mes, Model model) {

		List<PartCondition> partConditions = partConditionService.getAll();

		model.addAttribute("conditions", partConditions);

		model.addAttribute("partConditionAdd", new PartCondition());
		model.addAttribute("partConditionDelete", new PartCondition());

		return "admin/parts/admin-part-conditions";
	}

	@RequestMapping(value = "/add-part-condition", method = RequestMethod.POST)
	@Transactional
	public String addCondition(@Valid @ModelAttribute("partConditionAdd") PartCondition partConditionAdd,
			BindingResult theBindingResult, @RequestParam(required = false) String cndnId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<PartCondition> partConditions = partConditionService.getAll();

			model.addAttribute("conditions", partConditions);

			model.addAttribute("partConditionDelete", new PartCondition());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No condition added or changed.\n" + "</div>");

			return "admin/parts/admin-part-conditions";
		} else {

			if (!cndnId.isEmpty()) {

				PartCondition condition = partConditionService.getByCode(cndnId);

				condition.setName(partConditionAdd.getName());
				condition.setDescription(partConditionAdd.getDescription());

				partConditionService.update(condition);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + partConditionAdd.getName()
						+ "</strong>.\n" + "</div>");
			} else {

				partConditionService.save(partConditionAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + partConditionAdd.getName()
						+ "</strong>.\n" + "</div>");
			}

			return "redirect:admin-part-conditions";
		}

	}

	/*
	 * @RequestMapping(value = "/get-part-condition", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String
	 * getCondition(@RequestParam(required = true) String action,
	 * 
	 * @RequestParam(required = true) String object, Model model) {
	 * 
	 * PartCondition partCondition = partConditionService.getByCode(object);
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson( new String[] { partCondition.getCode(),
	 * partCondition.getName(), partCondition.getDescription() }); }
	 * 
	 * @RequestMapping(value = "/edit-part-condition", method = RequestMethod.POST)
	 * public String editCondition(@ModelAttribute("partConditionEdit")
	 * PartCondition partConditionEdit, Model model) {
	 * 
	 * if (partConditionEdit.getName() != null &&
	 * !partConditionEdit.getName().isEmpty()) {
	 * 
	 * partConditionService.update(partConditionEdit);
	 * 
	 * model.addAttribute("mes",
	 * "<div class=\"alert alert-success alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>Success.</strong> You have edited <strong>" +
	 * partConditionEdit.getName() + "</strong>.\n" + "</div>"); } else {
	 * 
	 * model.addAttribute("message",
	 * "<div class=\"alert alert-danger alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>No input.</strong> No changes made.\n" + "</div>"); }
	 * 
	 * return "redirect:admin-part-conditions"; }
	 */

	@RequestMapping(value = "/delete-part-condition", method = RequestMethod.POST)
	@Transactional
	public String deleteCondition(@ModelAttribute("partConditionDelete") PartCondition partConditionDelete,
			Model model) {

		if (partConditionService.delete(partConditionDelete)) {
			
			model.addAttribute("mes",
					"<div class=\"alert alert-warning alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success.</strong> You have deleted <strong>" + partConditionDelete.getName()
							+ "</strong>.\n" + "</div>");
		} else {
			
			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-part-conditions";
	}
}
