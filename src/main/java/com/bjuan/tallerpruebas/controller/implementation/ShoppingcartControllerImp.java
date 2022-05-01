package com.bjuan.tallerpruebas.controller.implementation;

import javax.validation.Valid;

import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.services.ShoppingCartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String save(Shoppingcartitem shoppingcart, @ModelAttribute(value="associatedProduct") @Valid Integer associatedProduct, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel"))
			service.save(shoppingcart, associatedProduct);
		return "redirect:/shoppingcart/";
	}
}
