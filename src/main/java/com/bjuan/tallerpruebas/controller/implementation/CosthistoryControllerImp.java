package com.bjuan.tallerpruebas.controller.implementation;

import javax.validation.Valid;

import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.services.ProductCostHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CosthistoryControllerImp {

	ProductCostHistoryService service;

	@Autowired
	public CosthistoryControllerImp(ProductCostHistoryService service) {
		this.service = service;
	}

	@GetMapping("/costhistory/")
	public String indexUser(Model model) {
		model.addAttribute("costhistory", service.findAll());
		return "costhistory/index";
	}

	@GetMapping("/costhistory/add")
	public String addUser(Model model) {
		model.addAttribute("costhistory", new Productcosthistory());
		model.addAttribute("associatedProduct", 0);
		return "costhistory/add";
	}
	@PostMapping("/costhistory/add")
	public String save(Productcosthistory costhistory, @ModelAttribute(value="associatedProduct") @Valid Integer associatedProduct, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel"))
			service.save(costhistory, associatedProduct);
		return "redirect:/costhistory/";
	}
}
