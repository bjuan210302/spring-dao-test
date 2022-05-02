package com.bjuan.tallerpruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.model.prod.Productmodel;
import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.model.user.MyUser;
import com.bjuan.tallerpruebas.model.user.MyUserType;
import com.bjuan.tallerpruebas.repositories.UserRepository;
import com.bjuan.tallerpruebas.services.ProductCostHistoryService;
import com.bjuan.tallerpruebas.services.ProductModelService;
import com.bjuan.tallerpruebas.services.ProductService;
import com.bjuan.tallerpruebas.services.ShoppingCartItemService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TallerpruebasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TallerpruebasApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ProductModelService pm, ProductService p, ProductCostHistoryService pch,
			ShoppingCartItemService sci, UserRepository u){
		return args -> {
			// Model
			Productmodel productmodel = new Productmodel();
			productmodel.setName("myProductmodel");
			productmodel.setInstructions("1. Do something.\n2. Lmao wrong");
			pm.save(productmodel);

			// Product 
			Product product = new Product();
			product.setName("myProduct");
			product.setProductnumber("myProductnumber");
			product.setSize(1);
			product.setWeight(new BigDecimal(1.0));
			product.setSellstartdate(LocalDate.now());;
			product.setSellenddate(LocalDate.now().plusDays(10));
			p.save(product, null, null);
			product = new Product();
			product.setName("myOtherproduct");
			product.setProductnumber("myOtherproductnumber");
			product.setSize(5);
			product.setWeight(new BigDecimal(3.0));
			product.setSellstartdate(LocalDate.now());;
			product.setSellenddate(LocalDate.now().plusDays(30));
			p.save(product, null, null);

			// ProductCostHistory
			Productcosthistory productch = new Productcosthistory();
			productch.setStandardcost(new BigDecimal(2.10));
			productch.setAssociatedproduct(1);
			productch.setModifieddate(LocalDate.now());
			productch.setEnddate(LocalDate.now().plusDays(10));
			pch.save(productch, productch.getAssociatedproduct());

			// ShoppingCartItem
			Shoppingcartitem scitem = new Shoppingcartitem();
			scitem.setQuantity(100);
			scitem.setDatecreated(LocalDate.now());
			scitem.setAssociatedproduct(1);
			sci.save(scitem, scitem.getAssociatedproduct());

			//Users
			MyUser user = new MyUser();
			user.setUsername("admin");
			user.setPassword("{noop}admin");
			user.setUsertype(MyUserType.ADMINISTRATOR);
			u.save(user);

			user = new MyUser();
			user.setUsername("operator");
			user.setPassword("{noop}operator");
			user.setUsertype(MyUserType.OPERATOR);
			u.save(user);
		};
	}
}
