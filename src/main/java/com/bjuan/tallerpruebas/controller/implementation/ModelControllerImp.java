package com.bjuan.tallerpruebas.controller.implementation;

import com.bjuan.tallerpruebas.model.prod.Productmodel;
import com.bjuan.tallerpruebas.services.ProductModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelControllerImp {

	ProductModelService service;

	@Autowired
	public ModelControllerImp(ProductModelService service) {
		this.service = service;
	}

	@GetMapping("/models/")
	public String indexUser(Model model) {
		model.addAttribute("models", service.findAll());
		return "models/index";
	}

	@GetMapping("/models/add")
	public String addUser(Model model) {
		model.addAttribute("pmodel", new Productmodel());
		return "models/add";
	}
	@PostMapping("/models/add")
	public String save(Productmodel pmodel, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel"))
			service.save(pmodel);;
		return "redirect:/models/";
	}
}
