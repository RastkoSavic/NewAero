package com.aerobase.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aerobase.app.entity.Company;
import com.aerobase.app.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/admin-companies", method = RequestMethod.GET)
	@Transactional
	public String adminCompanies(@ModelAttribute("mes") String mes, Model model) {

		List<Company> companies = companyService.getAll();

		model.addAttribute("companies", companies);

		return "admin/companies/admin-companies";
	}
}
