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

import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.service.PartCategoryService;
//import com.google.gson.Gson;

@Controller
public class PartCategoryController {

	@Autowired
	private PartCategoryService partCategoryService;

	@RequestMapping(value = "/admin-part-categories", method = RequestMethod.GET)
	public String adminCategories(@ModelAttribute("mes") String mes, Model model) {

		List<PartCategory> categories = partCategoryService.getAll();

		model.addAttribute("categories", categories);

		model.addAttribute("categoryAdd", new PartCategory());
		model.addAttribute("categoryDelete", new PartCategory());

		return "admin/parts/admin-part-categories";
	}

	@RequestMapping(value = "/add-category", method = RequestMethod.POST)
	@Transactional
	public String addCategory(@Valid @ModelAttribute("categoryAdd") PartCategory categoryAdd,
			BindingResult theBindingResult, @RequestParam(required = false) String ctgrId, Model model) {

		if (theBindingResult.hasErrors()) {

			List<PartCategory> categories = partCategoryService.getAll();

			model.addAttribute("categories", categories);

			model.addAttribute("categoryDelete", new PartCategory());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No category added.\n" + "</div>");

			return "admin/parts/admin-part-categories";
		} else {

			if (!ctgrId.isEmpty()) {

				PartCategory category = partCategoryService.getByCategory(ctgrId);

				category.setDescription(categoryAdd.getDescription());

				partCategoryService.update(category);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + categoryAdd.getCategory()
						+ "</strong>.\n" + "</div>");
			} else {

				partCategoryService.save(categoryAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + categoryAdd.getCategory()
						+ "</strong>.\n" + "</div>");
			}

			return "redirect:admin-part-categories";
		}

	}

	/*
	 * @RequestMapping(value = "/get-category", method = RequestMethod.GET)
	 * 
	 * @Transactional public @ResponseBody String getCategory(@RequestParam(required
	 * = true) String action,
	 * 
	 * @RequestParam(required = true) String object, Model model) {
	 * 
	 * model.addAttribute("formChoice", action);
	 * 
	 * PartCategory category = partCategoryService.getByCategory(object);
	 * 
	 * Gson gson = new Gson();
	 * 
	 * return gson.toJson(new String[] { category.getCategory(),
	 * category.getDescription() }); }
	 * 
	 * @RequestMapping(value = "/edit-category", method = RequestMethod.POST) public
	 * String editCategory(@ModelAttribute("categoryEdit") PartCategory
	 * categoryEdit, Model model) { if (categoryEdit.getCategory() != null &&
	 * !categoryEdit.getCategory().isEmpty()) {
	 * 
	 * partCategoryService.update(categoryEdit);
	 * 
	 * model.addAttribute("mes",
	 * "<div class=\"alert alert-success alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>Success.</strong> You have edited <strong>" +
	 * categoryEdit.getCategory() + "</strong>.\n" + "</div>"); } else {
	 * 
	 * model.addAttribute("message",
	 * "<div class=\"alert alert-danger alert-dismissable veci\">\n" +
	 * "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
	 * + "  <strong>No input.</strong> No changes made.\n" + "</div>"); }
	 * 
	 * return "redirect:admin-part-categories"; }
	 */

	@RequestMapping(value = "/delete-category", method = RequestMethod.POST)
	@Transactional
	public String deleteCategory(@ModelAttribute("categoryDelete") PartCategory categoryDelete, Model model) {

		if (partCategoryService.delete(categoryDelete)) {

			model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted <strong>" + categoryDelete.getCategory()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:admin-part-categories";
	}

}
