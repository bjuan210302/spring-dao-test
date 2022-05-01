package com.bjuan.tallerpruebas.controller.implementation;

import com.bjuan.tallerpruebas.model.prod.Product;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductControllerImp {

	ProductService service;

	@Autowired
	public ProductControllerImp(ProductService service) {
		this.service = service;
	}

	@GetMapping("/products/")
	public String indexUser(Model model) {
		model.addAttribute("products", service.findAll());
		return "products/index";
	}

	@GetMapping("/products/add")
	public String addUser(Model model) {
		model.addAttribute("product", new Product());
		return "products/add";
	}
	@PostMapping("/products/add")
	public String save(  @Validated(AddGroup.class) @ModelAttribute("product") Product product, BindingResult bindingResult,
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel"))
			return "redirect:/products/";

		boolean datesArePresent = product.getSellstartdate() != null && product.getSellenddate() != null;
		if(!datesArePresent || product.getSellenddate().isBefore(product.getSellstartdate()))
			bindingResult.addError(new FieldError("product", "sellstartdate", "La fecha de inicio debe ser antes de la fecha fin"));

		if (bindingResult.hasErrors())
			return "/products/add";

		service.save(product, null, null);
		return "redirect:/products/";
	}
}
