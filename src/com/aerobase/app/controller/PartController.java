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

import com.aerobase.app.entity.Part;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.service.PartCategoryService;
import com.aerobase.app.service.PartService;

@Controller
public class PartController {

	@Autowired
	private PartCategoryService partCategoryService;

	@Autowired
	private PartService partService;

	@RequestMapping(value = "/admin-parts", method = RequestMethod.GET)
	@Transactional
	public String adminParts(@ModelAttribute("mes") String mes, Model model) {

		List<Part> parts = partService.getAll();
		List<PartCategory> categories = partCategoryService.getAll();

		model.addAttribute("parts", parts);
		model.addAttribute("categories", categories);

		model.addAttribute("partAdd", new Part());
		model.addAttribute("partDelete", new Part());

		return "admin/parts/admin-parts";
	}

	@RequestMapping(value = "/add-part", method = RequestMethod.POST)
	@Transactional
	public String addPart(@Valid @ModelAttribute("partAdd") Part partAdd, BindingResult theBindingResult,
			@RequestParam(required = false) String prnmId, @RequestParam(required = false) String category,
			Model model) {

		if (theBindingResult.hasErrors()) {

			List<Part> parts = partService.getAll();
			List<PartCategory> categories = partCategoryService.getAll();

			model.addAttribute("parts", parts);
			model.addAttribute("categories", categories);

			model.addAttribute("partDelete", new Part());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No part added or changed.\n" + "</div>");

			return "admin/parts/admin-parts";
		} else {

			if (!prnmId.isEmpty()) {

				Part part = partService.getByPartNumber(prnmId);

				part.setDescription(partAdd.getDescription());

				partService.update(part);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + partAdd.getPartNumber()
						+ "</strong>.\n" + "</div>");
			} else {

				PartCategory partCategory = partCategoryService.getByCategory(category);
				partAdd.setCategory(partCategory);

				partService.save(partAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + partAdd.getPartNumber()
						+ "</strong>.\n" + "</div>");
			}

			return "redirect:admin-parts";
		}
	}

	@RequestMapping(value = "/delete-part", method = RequestMethod.POST)
	@Transactional
	public String deleteCountry(@ModelAttribute("partDelete") Part partDelete, Model model) {

		if (partService.delete(partDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + partDelete.getPartNumber()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-parts";
	}

	@RequestMapping(value = "/user-part", method = RequestMethod.GET)
	@Transactional
	public String userParts(@ModelAttribute("mes") String mes, Model model, HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");

		if (userModel != null) {
			List<Part> parts = partService.getAll();
			List<PartCategory> categories = partCategoryService.getAll();

			model.addAttribute("parts", parts);
			model.addAttribute("categories", categories);

			model.addAttribute("partAdd", new Part());

			return "main/user-part";
		} else {

			return "redirect:login";
		}
	}

	@RequestMapping(value = "/add-user-part", method = RequestMethod.POST)
	@Transactional
	public String addUserPart(@Valid @ModelAttribute("partAdd") Part partAdd, BindingResult theBindingResult,
			@RequestParam(required = false) String category, Model model) {

		if (theBindingResult.hasErrors()) {

			List<Part> parts = partService.getAll();
			List<PartCategory> categories = partCategoryService.getAll();

			model.addAttribute("parts", parts);
			model.addAttribute("categories", categories);

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No part added or changed.\n" + "</div>");

			return "main/user-part";
		} else {

			PartCategory partCategory = partCategoryService.getByCategory(category);
			partAdd.setCategory(partCategory);

			partService.save(partAdd);

			model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have added <strong>" + partAdd.getPartNumber() + "</strong>.\n"
					+ "</div>");

			return "redirect:user-part";
		}
	}
}
