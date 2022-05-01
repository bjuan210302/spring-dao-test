package com.bjuan.tallerpruebas.controller.implementation;

import com.bjuan.tallerpruebas.model.prod.Productmodel;
import com.bjuan.tallerpruebas.services.ProductModelService;
import com.bjuan.tallerpruebas.services.validation.AddGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String save(  @Validated(AddGroup.class) @ModelAttribute("pmodel") Productmodel pmodel, BindingResult bindingResult,
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel")) 
			return "redirect:/models/";

		if (bindingResult.hasErrors()){
			return "/models/add";
		}
		
		service.save(pmodel);
		return "redirect:/models/";
	}
}
