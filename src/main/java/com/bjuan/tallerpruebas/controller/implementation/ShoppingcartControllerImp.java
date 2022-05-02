package com.bjuan.tallerpruebas.controller.implementation;

import java.util.Optional;

import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.services.ProductService;
import com.bjuan.tallerpruebas.services.ShoppingCartItemService;
import com.bjuan.tallerpruebas.services.validation.AddGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingcartControllerImp {

	ShoppingCartItemService service;
	ProductService pservice;

	@Autowired
	public ShoppingcartControllerImp(ShoppingCartItemService service, ProductService pservice) {
		this.service = service;
		this.pservice = pservice;
	}

	@GetMapping("/shoppingcart/")
	public String index(Model model) {
		model.addAttribute("shoppingcart", service.findAll());
		return "shoppingcart/index";
	}

	@GetMapping("/shoppingcart/add")
	public String addGet(Model model) {
		model.addAttribute("shoppingcart", new Shoppingcartitem());
		model.addAttribute("availableproducts", pservice.findAll());
		return "shoppingcart/add";
	}
	@PostMapping("/shoppingcart/add")
	public String addPost(  @Validated(AddGroup.class) @ModelAttribute("shoppingcart") Shoppingcartitem shoppingcart, BindingResult bindingResult,
	Model model, @RequestParam(value = "action", required = true) String action) {
		if (action.equals("Cancel"))
			return "redirect:/shoppingcart/";
		
		if (bindingResult.hasErrors())
			return "/shoppingcart/add";
				
		service.save(shoppingcart, shoppingcart.getAssociatedproduct());
		return "redirect:/shoppingcart/";
	}

	@GetMapping("/shoppingcart/edit/{id}")
	public String updateGet(@PathVariable("id") Integer id, Model model) {
		Optional<Shoppingcartitem> item = service.find(id);
		
		if (item.isEmpty())
			throw new IllegalArgumentException("Invalid Id:" + id);
		
		model.addAttribute("availableproducts", pservice.findAll());
		model.addAttribute("shoppingcart", item.get());
		return "shoppingcart/add";
	}

	public String getStringOfProduct(Integer id){
		return this.pservice.find(id).get().toString();
	}
}
