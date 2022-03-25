package com.bjuan.tallerpruebas.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.services.ShoppingCartItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class ShoppingCartItemTest {
	
	ShoppingCartItemService SCIService;

	Shoppingcartitem sci;
	Product p;
	final Integer shoppingCartID = 2103;
	final Integer productAssignedID = 2016;

	@BeforeEach
	void setupProductModel(){
		this.sci = new Shoppingcartitem();
		this.sci.setShoppingcartid(shoppingCartID);
		//Ok att
		this.sci.setQuantity(1);
		long now = System.currentTimeMillis();
		this.sci.setDatecreated(new Timestamp(now - 1));

		this.p = new Product();
		this.p.setProductid(productAssignedID);

		
		// Mockito.when(this.PService.find(productAssignedID)).thenReturn(Optional.of(this.p));
		// Mockito.when(this.SCIRepo.findById(shoppingCartID)).thenReturn(Optional.of(this.sci));
	}
	
	//ADD TEST
	@Test
	void addShoppingCartItemProductExistsTest(){
		this.SCIService.saveShoppingItem(this.sci, productAssignedID);

		Shoppingcartitem retrived = this.SCIService.find(shoppingCartID).get();
		assertNotNull(retrived);
		assertEquals(shoppingCartID, retrived.getShoppingcartid());
		assertEquals(productAssignedID, retrived.getProductid());
	}
	
}
