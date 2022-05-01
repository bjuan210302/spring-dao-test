package com.bjuan.tallerpruebas.controller.implementation;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String save(Product product, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel"))
			service.save(product, null, null);
		return "redirect:/products/";
	}
}
