package com.aerobase.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aerobase.app.entity.AircraftType;
import com.aerobase.app.entity.Company;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.entity.PartCondition;
import com.aerobase.app.entity.StockPart;
import com.aerobase.app.entity.User;
import com.aerobase.app.entity.UserSettings;
import com.aerobase.app.entity.UserStatus;
import com.aerobase.app.model.CompanySettingsModel;
import com.aerobase.app.model.MessageModel;
import com.aerobase.app.model.StockPartModel;
import com.aerobase.app.model.UserModel;
import com.aerobase.app.service.AircraftModelService;
import com.aerobase.app.service.AircraftTypeService;
import com.aerobase.app.service.CompanyService;
import com.aerobase.app.service.PartCategoryService;
import com.aerobase.app.service.PartConditionService;
import com.aerobase.app.service.StockPartService;
import com.aerobase.app.service.UserService;
import com.aerobase.app.service.UserSettingsService;
import com.aerobase.app.service.UserStatusService;
import com.google.gson.Gson;

@Controller
public class CompanyProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserStatusService userStatusService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PartCategoryService categoryService;

	@Autowired
	private PartConditionService conditionService;

	@Autowired
	private AircraftTypeService typeService;

	@Autowired
	private AircraftModelService modelService;

	@Autowired
	private StockPartService stockPartService;

	@Autowired
	private UserSettingsService userSettingsService;

	@RequestMapping(value = "/company-profile", method = RequestMethod.GET)
	@Transactional
	public String companyProfile(@ModelAttribute("mes") String mes, @ModelAttribute("mesu") String mesu,
			@ModelAttribute("mest") String mest, @ModelAttribute("messt") String messt, Model model,
			HttpSession session) {

		UserModel userModel = (UserModel) session.getAttribute("currentUser");

		User user = userService.getById(userModel.getId());
		System.out.println(user.getFirstName());
		Company company = user.getCompany();

		CompanySettingsModel companySettings = companyService.forSettingsModel(company.getId());

		Set<User> users = company.getUsers();

		Set<StockPart> stockParts = company.getStock().getStockParts();

		for (StockPart stockPart : stockParts) {
			Hibernate.initialize(stockPart.getPart());
		}

		List<PartCategory> categories = categoryService.getAll();
		List<PartCondition> conditions = conditionService.getAll();
		List<AircraftType> types = typeService.getAll();

		model.addAttribute("companySettings", companySettings);
		model.addAttribute("users", users);
		model.addAttribute("user", user);

		model.addAttribute("userAdd", new User());
		model.addAttribute("userDelete", new User());

		model.addAttribute("company", company);

		model.addAttribute("stockParts", stockParts);

		model.addAttribute("categories", categories);
		model.addAttribute("conditions", conditions);
		model.addAttribute("types", types);

		model.addAttribute("stockAdd", new StockPartModel());
		model.addAttribute("stockPartDelete", new StockPart());

		model.addAttribute("messagePost", new MessageModel());

		return "main/company-profile";
	}

	@RequestMapping(value = "/company-sett", method = RequestMethod.POST)
	@Transactional
	public String companySettings(@Valid @ModelAttribute("companySettings") CompanySettingsModel companySettings,
			BindingResult theBindingResult, @RequestParam(required = true) Integer coId, Model model,
			HttpSession session) {

		if (theBindingResult.hasErrors()) {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			User user = userService.getById(userModel.getId());
			Company company = user.getCompany();

			// CompanySettingsModel companySettings =
			// companyService.forSettingsModel(company.getId());
			Set<User> users = new HashSet<>();
			for (User us : company.getUsers()) {
				Hibernate.initialize(us);
				users.add(us);
			}

			Set<StockPart> stockParts = company.getStock().getStockParts();

			for (StockPart stockPart : stockParts) {
				Hibernate.initialize(stockPart.getPart());
			}

			List<PartCategory> categories = categoryService.getAll();
			List<PartCondition> conditions = conditionService.getAll();
			List<AircraftType> types = typeService.getAll();

			model.addAttribute("users", users);
			model.addAttribute("user", user);

			model.addAttribute("userAdd", new User());
			model.addAttribute("userDelete", new User());

			model.addAttribute("company", company);

			model.addAttribute("stockParts", stockParts);

			model.addAttribute("categories", categories);
			model.addAttribute("conditions", conditions);
			model.addAttribute("types", types);

			model.addAttribute("stockAdd", new StockPartModel());
			model.addAttribute("stockPartDelete", new StockPart());

			model.addAttribute("messagePost", new MessageModel());

			model.addAttribute("mes", "<div id='mes' class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No changes made.\n" + "</div>");

			return "main/company-profile";
		} else {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			companyService.setAll(companySettings, userModel.getCompanyId());

			model.addAttribute("mes", "<div id='mes' class=\"alert alert-success alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success!</strong> You have changed settings <strong>" + "</strong>.\n" + "</div>");

			return "redirect:company-profile";
		}

	}

	@RequestMapping(value = "/add-company-user", method = RequestMethod.POST)
	@Transactional
	public String addUser(@Valid @ModelAttribute("userAdd") User userAdd, BindingResult theBindingResult,
			@RequestParam(required = true) Integer compId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			User user = userService.getById(userModel.getId());
			Company company = user.getCompany();

			CompanySettingsModel companySettings = companyService.forSettingsModel(company.getId());
			Set<User> users = new HashSet<>();
			for (User us : company.getUsers()) {
				Hibernate.initialize(us);
				users.add(us);
			}

			Set<StockPart> stockParts = company.getStock().getStockParts();

			for (StockPart stockPart : stockParts) {
				Hibernate.initialize(stockPart.getPart());
			}

			List<PartCategory> categories = categoryService.getAll();
			List<PartCondition> conditions = conditionService.getAll();
			List<AircraftType> types = typeService.getAll();

			model.addAttribute("companySettings", companySettings);
			model.addAttribute("users", users);
			model.addAttribute("user", user);

			model.addAttribute("userDelete", new User());

			model.addAttribute("company", company);

			model.addAttribute("stockParts", stockParts);

			model.addAttribute("categories", categories);
			model.addAttribute("conditions", conditions);
			model.addAttribute("types", types);

			model.addAttribute("stockAdd", new StockPartModel());
			model.addAttribute("stockPartDelete", new StockPart());

			model.addAttribute("messagePost", new MessageModel());

			model.addAttribute("mesu", "<div id='mes' class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No user added.\n" + "</div>");

			return "main/company-profile";
		} else {

			User userCheck = userService.getByEmail(userAdd.getEmail());
			if (userCheck != null) {

				model.addAttribute("mesu", "<div id='mes' class=\"alert alert-danger alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Wrong input!</strong> Email already exists.\n" + "</div>");

				return "redirect:company-profile";
			} else {

				UserModel userModel = (UserModel) session.getAttribute("currentUser");

				Company company = companyService.getById(userModel.getCompanyId());
				userAdd.setCompany(company);
				UserStatus status = userStatusService.getByStatus("User");
				userAdd.setStatus(status);

				int id = userService.save(userAdd);

				User user = userService.getById(id);

				UserSettings userSettings = new UserSettings(user, (byte) 1, (byte) 1, (byte) 1, (byte) 1);

				userSettingsService.save(userSettings);
				model.addAttribute("mesu", "<div id='mes' class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have added <strong>" + userAdd.getFirstName() + " "
						+ userAdd.getLastName() + "</strong>.\n" + "</div>");
				return "redirect:company-profile";
			}

		}
	}

	@RequestMapping(value = "/delete-company-user", method = RequestMethod.POST)
	public String deleteService(@ModelAttribute("userDelete") User userDelete, Model model) {

		if (userService.delete(userDelete)) {

			model.addAttribute("mest", "<div id='mes' class=\"alert alert-warning alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Success.</strong> You have deleted user with id <strong>" + userDelete.getId()
					+ "</strong>.\n" + "</div>");
		} else {

			model.addAttribute("mest", "<div id='mes' class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Delete failed.</strong> Remove containing elements first <strong>" + "</strong>.\n"
					+ "</div>");
		}

		return "redirect:company-profile";
	}

	@RequestMapping(value = "/select-type-for-stock", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody String selectType(@RequestParam(required = true) String object, Model model) {

		if (Integer.parseInt(object) == 0) {

			return null;
		} else {

			Gson gson = new Gson();

			return gson.toJson(modelService.listForModel(Integer.parseInt(object)));
		}

	}

	@RequestMapping(value = "/add-stock-part", method = RequestMethod.POST)
	@Transactional
	public String addStockPart(@Valid @ModelAttribute("stockAdd") StockPartModel stockAdd,
			BindingResult theBindingResult, @RequestParam(required = false) Integer stprId,
			@RequestParam(required = false) String category, @RequestParam(required = false) String condition,
			@RequestParam(required = false) Integer variantId, @RequestParam(required = false) Integer costId,
			@RequestParam(required = false) Integer usstId, Model model, HttpSession session) {

		if (theBindingResult.hasErrors()) {

			UserModel userModel = (UserModel) session.getAttribute("currentUser");

			User user = userService.getById(userModel.getId());
			Company company = user.getCompany();

			CompanySettingsModel companySettings = companyService.forSettingsModel(company.getId());

			Set<User> users = company.getUsers();

			Set<StockPart> stockParts = company.getStock().getStockParts();

			for (StockPart stockPart : stockParts) {
				Hibernate.initialize(stockPart.getPart());
			}

			List<PartCategory> categories = categoryService.getAll();
			List<PartCondition> conditions = conditionService.getAll();
			List<AircraftType> types = typeService.getAll();

			model.addAttribute("companySettings", companySettings);
			model.addAttribute("users", users);
			model.addAttribute("user", user);

			model.addAttribute("userAdd", new User());
			model.addAttribute("userDelete", new User());

			model.addAttribute("company", company);

			model.addAttribute("stockParts", stockParts);

			model.addAttribute("categories", categories);
			model.addAttribute("conditions", conditions);
			model.addAttribute("types", types);

			model.addAttribute("stockPartDelete", new StockPart());

			model.addAttribute("messagePost", new MessageModel());

			model.addAttribute("messt", "<div id='mes' class=\"alert alert-danger alert-dismissable veci\">\n"
					+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
					+ "  <strong>Wrong input!</strong> No changes made.\n" + "</div>");

			return "main/company-profile";
		} else {

			if (stprId > 0) {

				StockPart stockPart = stockPartService.getById(stprId);

				stockPart.setNotes(stockAdd.getNotes());
				stockPart.setPrice(stockAdd.getPrice());
				stockPart.setQuantity(stockAdd.getQuantity());

				stockPartService.update(stockPart);

				model.addAttribute("messt", "<div id='mes' class=\"alert alert-success alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success!</strong> You have edited <strong>" + stockAdd.getPartNumber()
						+ "</strong>.\n" + "</div>");

				return "redirect:company-profile";
			} else {

				if (!condition.equals("0")) {
					stockAdd.setCategory(category);
					stockAdd.setConditionCode(condition);
					stockAdd.setCompanyId(costId);

					stockPartService.save(stockAdd, variantId, usstId);

					model.addAttribute("messt", "<div id='mes' class=\"alert alert-success alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Success!</strong> You have added new stock collection <strong>"
							+ "</strong>.\n" + "</div>");

				} else {

					model.addAttribute("messt", "<div id='mes' class=\"alert alert-danger alert-dismissable veci\">\n"
							+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
							+ "  <strong>Wrong input!</strong> Condition must be selected.\n" + "</div>");
				}
				return "redirect:company-profile";

			}
		}

	}

	@RequestMapping(value = "/delete-stock-part", method = RequestMethod.POST)
	@Transactional
	public String deleteCondition(@ModelAttribute("stockPartDelete") StockPart stockPartDelete, Model model) {

		stockPartService.delete(stockPartDelete);

		model.addAttribute("messt",
				"<div id='mes' class=\"alert alert-warning alert-dismissable veci\">\n"
						+ "  <a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>\n"
						+ "  <strong>Success.</strong> You have deleted stock with id <strong>"
						+ stockPartDelete.getId() + "</strong>.\n" + "</div>");

		return "redirect:company-profile";
	}

}
