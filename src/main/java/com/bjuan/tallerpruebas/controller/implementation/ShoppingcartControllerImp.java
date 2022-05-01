package com.bjuan.tallerpruebas.controller.implementation;

import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.services.ShoppingCartItemService;
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
public class ShoppingcartControllerImp {

	ShoppingCartItemService service;

	@Autowired
	public ShoppingcartControllerImp(ShoppingCartItemService service) {
		this.service = service;
	}

	@GetMapping("/shoppingcart/")
	public String indexUser(Model model) {
		model.addAttribute("shoppingcart", service.findAll());
		return "shoppingcart/index";
	}

	@GetMapping("/shoppingcart/add")
	public String addUser(Model model) {
		model.addAttribute("shoppingcart", new Shoppingcartitem());
		model.addAttribute("associatedProduct", 0);
		return "shoppingcart/add";
	}
	@PostMapping("/shoppingcart/add")
	public String save(  @Validated(AddGroup.class) @ModelAttribute("shoppingcart") Shoppingcartitem shoppingcart, BindingResult bindingResult,
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel"))
			return "redirect:/shoppingcart/";
		
		if (bindingResult.hasErrors())
			return "/shoppingcart/add";
				
		service.save(shoppingcart, shoppingcart.getAssociatedproduct());
		return "redirect:/shoppingcart/";
	}
}
