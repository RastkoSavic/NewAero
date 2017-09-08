package com.aerobase.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aerobase.app.dao.AircraftVariantDAO;
import com.aerobase.app.dao.CompanyStockDAO;
import com.aerobase.app.dao.PartCategoryDAO;
import com.aerobase.app.dao.PartConditionDAO;
import com.aerobase.app.dao.PartDAO;
import com.aerobase.app.dao.StockPartDAO;
import com.aerobase.app.dao.UserDAO;
import com.aerobase.app.entity.AircraftVariant;
import com.aerobase.app.entity.CompanyStock;
import com.aerobase.app.entity.Part;
import com.aerobase.app.entity.PartCategory;
import com.aerobase.app.entity.PartCondition;
import com.aerobase.app.entity.StockPart;
import com.aerobase.app.entity.User;
import com.aerobase.app.model.StockPartModel;
import com.aerobase.app.service.StockPartService;

@Service
public class StockPartServiceImpl implements StockPartService {

	@Autowired
	private StockPartDAO stockPartDAO;

	@Autowired
	private PartDAO partDAO;

	@Autowired
	private PartCategoryDAO partCategoryDAO;

	@Autowired
	private PartConditionDAO partConditionDAO;

	@Autowired
	private CompanyStockDAO companyStockDao;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AircraftVariantDAO aircraftVariantDAO;


	@Override
	@Transactional
	public void save(StockPartModel stockPartModel, Integer variantId, Integer usstId) {

		Part part;
		part = partDAO.getByPartNumber(stockPartModel.getPartNumber());

		if (part == null) {

			part = new Part(stockPartModel.getPartNumber(), stockPartModel.getDescription());
			PartCategory partCategory = partCategoryDAO.getByCategory(stockPartModel.getCategory());
			part.setCategory(partCategory);
			partDAO.save(part);
		}

		if (variantId > 0) {

			AircraftVariant aircraftVariant = aircraftVariantDAO.getById(variantId);

			aircraftVariant.getParts().add(part);
			part.getAircraftVariants().add(aircraftVariant);
			aircraftVariantDAO.save(aircraftVariant);
			partDAO.save(part);
		}

		CompanyStock companyStock = companyStockDao.getByCompanyId(stockPartModel.getCompanyId());
		PartCondition partCondition = partConditionDAO.getByCode(stockPartModel.getConditionCode());
		User user = userDAO.getById(usstId);

		StockPart stockPart = new StockPart(stockPartModel.getQuantity());

		stockPart.setPart(part);
		part.getStockParts().add(stockPart);
		stockPart.setCondition(partCondition);
		partCondition.getStockParts().add(stockPart);
		stockPart.setUser(user);
		user.getStockParts().add(stockPart);
		stockPart.setStock(companyStock);
		companyStock.getStockParts().add(stockPart);
		stockPart.setPrice(stockPartModel.getPrice());
		stockPart.setNotes(stockPartModel.getNotes());

		stockPartDAO.save(stockPart);
	}

	@Override
	@Transactional
	public List<StockPartModel> getAll() {

		List<StockPartModel> stockPartModels = new ArrayList<>();
		List<StockPart> stockParts = stockPartDAO.getAll();

		for (StockPart stockPart : stockParts) {

			StockPartModel stockPartModel = new StockPartModel(stockPart.getPart().getPartNumber(),
					stockPart.getPart().getDescription(), stockPart.getQuantity(), stockPart.getPrice(), "",
					stockPart.getNotes());

			stockPartModel.setId(stockPart.getId());
			stockPartModel.setCreated(stockPart.getCreated());

			stockPartModel.setCategory(stockPart.getPart().getCategory().getCategory());
			stockPartModel.setCondition(stockPart.getCondition().getName());
			stockPartModel.setConditionCode(stockPart.getCondition().getCode());
			stockPartModel.setCompany(stockPart.getStock().getCompany().getName());
			stockPartModel.setCompanyId(stockPart.getStock().getCompany().getId());
			stockPartModel.setUserId(stockPart.getUser().getId());

			stockPartModels.add(stockPartModel);
		}

		return stockPartModels;
	}

	@Override
	@Transactional
	public StockPart getById(int id) {

		return stockPartDAO.getById(id);
	}

	@Override
	@Transactional
	public void update(StockPart stockPart) {

		stockPartDAO.update(stockPart);
	}

	@Override
	@Transactional
	public void delete(StockPart stockPart) {

		stockPartDAO.delete(stockPart);
	}

	@Override
	@Transactional
	public List<StockPartModel> findResults(String pNumber) {

		List<StockPartModel> stockPartModels = new ArrayList<>();
		List<StockPart> stockParts = stockPartDAO.findByPart(pNumber);

		for (StockPart stockPart : stockParts) {

			StockPartModel stockPartModel = new StockPartModel(stockPart.getPart().getPartNumber(),
					stockPart.getPart().getDescription(), stockPart.getQuantity(), stockPart.getPrice(), "",
					stockPart.getNotes());

			stockPartModel.setId(stockPart.getId());
			stockPartModel.setCreated(stockPart.getCreated());

			stockPartModel.setCategory(stockPart.getPart().getCategory().getCategory());
			stockPartModel.setCondition(stockPart.getCondition().getName());
			stockPartModel.setConditionCode(stockPart.getCondition().getCode());
			stockPartModel.setCompany(stockPart.getStock().getCompany().getName());
			stockPartModel.setCompanyId(stockPart.getStock().getCompany().getId());
			stockPartModel.setUserId(stockPart.getUser().getId());

			stockPartModels.add(stockPartModel);
		}

		return stockPartModels;
	}

	@Override
	public List<StockPartModel> getLatest() {

		List<StockPartModel> stockPartModels = new ArrayList<>();
		List<StockPart> stockParts = stockPartDAO.findLatest();

		for (StockPart stockPart : stockParts) {

			StockPartModel stockPartModel = new StockPartModel(stockPart.getPart().getPartNumber(),
					stockPart.getPart().getDescription(), stockPart.getQuantity(), stockPart.getPrice(), "",
					stockPart.getNotes());

			stockPartModel.setId(stockPart.getId());
			stockPartModel.setCreated(stockPart.getCreated());

			stockPartModel.setCategory(stockPart.getPart().getCategory().getCategory());
			stockPartModel.setCondition(stockPart.getCondition().getName());
			stockPartModel.setConditionCode(stockPart.getCondition().getCode());
			stockPartModel.setCompany(stockPart.getStock().getCompany().getName());
			stockPartModel.setCompanyId(stockPart.getStock().getCompany().getId());
			stockPartModel.setUserId(stockPart.getUser().getId());

			stockPartModels.add(stockPartModel);
		}

		return stockPartModels;
	}

}
