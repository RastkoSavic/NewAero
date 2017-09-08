package com.aerobase.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aerobase.app.dao.PartCategoryDAO;
import com.aerobase.app.entity.Part;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.model.StockPartModel;
import com.aerobase.app.service.PartService;
import com.aerobase.app.service.StockPartService;

@Controller
public class PartMainController {

	@Autowired
	private PartService partService;

	@Autowired
	private PartCategoryDAO partCategoryDAO;

	@Autowired
	private StockPartService stockPartService;

	@RequestMapping("/parts")
	@Transactional
	public String parts(@ModelAttribute("mes") String mes, Model model) {

		List<PartCategory> categories = partCategoryDAO.getAll();

		model.addAttribute("categories", categories);

		model.addAttribute("partAdd", new Part());

		return "main/parts";
	}

	@RequestMapping(value = "/search-parts", method = RequestMethod.POST)
	@Transactional
	public String searchParts(@RequestParam(required = true) String pNumber, Model model, HttpSession session) {

		if (pNumber != null && !pNumber.isEmpty()) {

			List<StockPartModel> stockPartModels = stockPartService.findResults(pNumber);

			if (!stockPartModels.isEmpty()) {

				session.setAttribute("partFound", null);
				session.setAttribute("stockPartModels", stockPartModels);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Result match!</strong> Available stock for <strong>" + pNumber + "</strong>.\n"
						+ "</div>");
			} else {

				session.setAttribute("stockPartModels", null);

				Part part = partService.getByPartNumber(pNumber);

				if (part != null) {

					session.setAttribute("partFound", part);

					model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Part not on stock!</strong> Database info for <strong>" + pNumber
							+ "</strong>.\n" + "</div>");
				} else {

					session.setAttribute("partFound", null);

					model.addAttribute("mes", "<div class=\"alert alert-warning alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Part not in database!</strong> <a href='user-part'>Add " + pNumber
							+ " to database?</a> <strong>" + "</strong>.\n" + "</div>");
				}
			}
		} else {

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No results found.\n" + "</div>");
		}

		return "redirect:parts";
	}

}
