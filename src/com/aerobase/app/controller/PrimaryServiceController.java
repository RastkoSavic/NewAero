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

import com.aerobase.app.entity.PrimaryService;
import com.aerobase.app.service.PrimaryServiceService;
//import com.google.gson.Gson;

@Controller
public class PrimaryServiceController {

	@Autowired
	private PrimaryServiceService serviceService;

	@RequestMapping(value = "/admin-primary-services", method = RequestMethod.GET)
	public String adminPrimaryServices(@ModelAttribute("mes") String mes, Model model) {

		List<PrimaryService> primaryServices = serviceService.getAll();

		model.addAttribute("services", primaryServices);

		model.addAttribute("primaryServiceAdd", new PrimaryService());
		model.addAttribute("primaryServiceDelete", new PrimaryService());

		return "admin/companies/admin-primary-services";
	}

	@RequestMapping(value = "/add-primary-service", method = RequestMethod.POST)
	@Transactional
	public String addService(@Valid @ModelAttribute("primaryServiceAdd") PrimaryService primaryServiceAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer servId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<PrimaryService> primaryServices = serviceService.getAll();

			model.addAttribute("services", primaryServices);

			model.addAttribute("primaryServiceDelete", new PrimaryService());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No service added or changed.\n" + "</div>");

			return "admin/companies/admin-primary-services";
		} else {

			if (servId > 0) {

				PrimaryService service = serviceService.getById(servId);
				service.setService(primaryServiceAdd.getService());
				service.setDescription(primaryServiceAdd.getDescription());
				serviceService.update(service);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + primaryServiceAdd.getService()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-primary-services";
			} else {

				serviceService.save(primaryServiceAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + primaryServiceAdd.getService()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-primary-services";
			}
		}

	}

	/*
	 * @RequestMapping(value = "/get-primary-service", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String getService(@RequestParam(required
	 * = true) String action,
	 * 
	 * @RequestParam(required = true) Integer object, Model model) {
	 * 
	 * PrimaryService primaryService = serviceService.getById(object);
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson(new String[] { String.valueOf(primaryService.getId()),
	 * primaryService.getService(), primaryService.getDescription() }); }
	 * 
	 * @RequestMapping(value = "/edit-primary-service", method = RequestMethod.POST)
	 * public String editService(@ModelAttribute("primaryServiceEdit")
	 * PrimaryService primaryServiceEdit, Model model) {
	 * 
	 * if (primaryServiceEdit.getService() != null &&
	 * !primaryServiceEdit.getService().isEmpty()) {
	 * 
	 * serviceService.update(primaryServiceEdit);
	 * 
	 * model.addAttribute("mes",
	 * "<div class=\"alert alert-success alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>Success.</strong> You have edited <strong>" +
	 * primaryServiceEdit.getService() + "</strong>.\n" + "</div>"); } else {
	 * 
	 * model.addAttribute("message",
	 * "<div class=\"alert alert-danger alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>No input.</strong> No changes made.\n" + "</div>"); }
	 * 
	 * return "redirect:admin-primary-services"; }
	 */

	@RequestMapping(value = "/delete-primary-service", method = RequestMethod.POST)
	public String deleteService(@ModelAttribute("primaryServiceDelete") PrimaryService primaryServiceDelete,
			Model model) {

		if (serviceService.delete(primaryServiceDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + primaryServiceDelete.getService()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-primary-services";
	}

}
