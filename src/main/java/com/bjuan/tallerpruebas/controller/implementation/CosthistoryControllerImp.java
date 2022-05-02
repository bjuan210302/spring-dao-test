package com.bjuan.tallerpruebas.controller.implementation;

import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.services.ProductCostHistoryService;
import com.bjuan.tallerpruebas.services.ProductService;
import com.bjuan.tallerpruebas.services.validation.AddGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CosthistoryControllerImp {

	ProductCostHistoryService service;
	ProductService pservice;

	@Autowired
	public CosthistoryControllerImp(ProductCostHistoryService service, ProductService pservice) {
		this.service = service;
		this.pservice = pservice;
	}

	@GetMapping("/costhistory/")
	public String index(Model model) {
		model.addAttribute("costhistory", service.findAll());
		return "costhistory/index";
	}

	@GetMapping("/costhistory/add")
	public String addGet(Model model) {
		model.addAttribute("costhistory", new Productcosthistory());
		model.addAttribute("availableproducts", pservice.findAll());
		return "costhistory/add";
	}
	@PostMapping("/costhistory/add")
	public String addPost( @Validated(AddGroup.class) @ModelAttribute("costhistory") Productcosthistory costhistory, BindingResult bindingResult,
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel"))
			return "redirect:/costhistory/";

		boolean datesArePresent = costhistory.getModifieddate() != null && costhistory.getEnddate() != null;
		if(!datesArePresent || costhistory.getEnddate().isBefore(costhistory.getModifieddate()))
			bindingResult.addError(new FieldError("costhistory", "modifieddate", "La fecha de inicio debe ser antes de la fecha fin"));

		if (bindingResult.hasErrors())
			return "/costhistory/add";
		
		service.save(costhistory, costhistory.getAssociatedproduct());
		return "redirect:/costhistory/";
	}

	@GetMapping("/costhistory/edit/{id}")
	public String updateGet(@PathVariable("id") Integer id, Model model) {
		Optional<Productcosthistory> item = service.find(id);
		
		if (item.isEmpty())
			throw new IllegalArgumentException("Invalid Id:" + id);
		
		model.addAttribute("availableproducts", pservice.findAll());
		model.addAttribute("costhistory", item.get());
		return "costhistory/add";
	}
}
