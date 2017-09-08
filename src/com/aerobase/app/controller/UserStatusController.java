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

import com.aerobase.app.entity.UserStatus;
import com.aerobase.app.service.UserStatusService;

@Controller
public class UserStatusController {

	@Autowired
	private UserStatusService userStatusService;

	@RequestMapping(value = "/admin-company-hub", method = RequestMethod.GET)
	public String adminUserStatuses(@ModelAttribute("mes") String mes, Model model) {

		List<UserStatus> userStatuses = userStatusService.getAll();

		model.addAttribute("statuses", userStatuses);

		model.addAttribute("userStatusAdd", new UserStatus());
		model.addAttribute("userStatusDelete", new UserStatus());

		return "admin/companies/admin-company-hub";
	}

	@RequestMapping(value = "/add-user-status", method = RequestMethod.POST)
	@Transactional
	public String addStatus(@Valid @ModelAttribute("userStatusAdd") UserStatus userStatusAdd,
			BindingResult theBindingResult, @RequestParam(required = false) String stat, Model model) {

		if (theBindingResult.hasErrors()) {

			List<UserStatus> userStatuses = userStatusService.getAll();

			model.addAttribute("statuses", userStatuses);

			model.addAttribute("userStatusDelete", new UserStatus());

			model.addAttribute("mes", "<div class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No status added or changed.\n" + "</div>");

			return "admin/companies/admin-company-hub";
		} else {

			if (!stat.equals("")) {

				UserStatus status = userStatusService.getByStatus(stat);
				status.setDescription(userStatusAdd.getDescription());
				userStatusService.update(status);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + userStatusAdd.getStatus()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-company-hub";
			} else {

				userStatusService.save(userStatusAdd);

				model.addAttribute("mes", "<div class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + userStatusAdd.getStatus()
						+ "</strong>.\n" + "</div>");

				return "redirect:admin-company-hub";

			}

		}
	}
	
	@RequestMapping(value = "/delete-user-status", method = RequestMethod.POST)
	public String deleteVariantType(@ModelAttribute("userStatusDelete") UserStatus userStatusDelete,
			Model model) {

		userStatusService.delete(userStatusDelete);

		model.addAttribute("mes",
				"<div class=\"alert alert-warning alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success.</strong> You have deleted <strong>" + userStatusDelete.getStatus()
						+ "</strong>.\n" + "</div>");

		return "redirect:admin-company-hub";
	}
}
