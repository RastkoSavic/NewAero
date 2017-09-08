package com.aerobase.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aerobase.app.model.StockPartModel;
import com.aerobase.app.service.StockPartService;

@Controller
public class StockPartController {

	@Autowired
	private StockPartService stockPartService;

	@RequestMapping("/admin-stock-parts")
	@Transactional
	public String adminStockParts(@ModelAttribute("mes") String mes, Model model) {

		List<StockPartModel> stockParts = stockPartService.getAll();

		model.addAttribute("stockParts", stockParts);

		return "admin/parts/admin-stock-parts";
	}
}
