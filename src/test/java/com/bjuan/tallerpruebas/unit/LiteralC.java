package com.bjuan.tallerpruebas.unit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.repositories.IShoppingCartItemRepository;
import com.bjuan.tallerpruebas.services.ProductService;
import com.bjuan.tallerpruebas.services.ShoppingCartItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class LiteralC {
	
	@Mock
	IShoppingCartItemRepository SCIRepo;
	@Mock
	ProductService PService;

	@InjectMocks
	ShoppingCartItemService SCIService;

	Shoppingcartitem sci;
	Product p;
	final Integer shoppingCartID = 2103;
	final Integer productAssignedID = 2016;

	@BeforeEach
	void setup(){
		this.sci = new Shoppingcartitem();
		this.sci.setShoppingcartid(shoppingCartID);
		//Ok att
		this.sci.setQuantity(1);
		long now = System.currentTimeMillis();
		this.sci.setDatecreated(new Timestamp(now - 1));

		this.p = new Product();
		this.p.setProductid(productAssignedID);
		

		Mockito.when(this.PService.find(productAssignedID)).thenReturn(Optional.of(this.p));
		Mockito.when(this.SCIRepo.findById(shoppingCartID)).thenReturn(Optional.of(this.sci));
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
	@Test
	void addShoppingCartItemProductNotExistsTest(){
		Mockito.when(this.PService.find(productAssignedID)).thenReturn(Optional.empty());
		assertThrows(InvalidParameterException.class, () -> {
			this.SCIService.saveShoppingItem(this.sci, productAssignedID);
		});
	}
	@Test
	void addShoppingCartItemProductNullTest(){
		assertThrows(InvalidParameterException.class, () -> {
			this.SCIService.saveShoppingItem(this.sci, null);
		});
	}
	@Test
	void addShoppingCartItemProductQuantityOKTest(){
		this.SCIService.saveShoppingItem(this.sci, productAssignedID);

		Shoppingcartitem retrived = this.SCIService.find(shoppingCartID).get();
		assertNotNull(retrived);
		assertEquals(shoppingCartID, retrived.getShoppingcartid());
		assertEquals(productAssignedID, retrived.getProductid());
	}
	@Test
	void addShoppingCartItemProductQuantityZeroTest(){
		this.sci.setQuantity(0);

		assertThrows(InvalidParameterException.class, () -> {
			this.SCIService.saveShoppingItem(this.sci, productAssignedID);
		});
	}
	@Test
	void addShoppingCartItemProductQuantityBelowZeroTest(){
		this.sci.setQuantity(-1);
		assertThrows(InvalidParameterException.class, () -> {
			this.SCIService.saveShoppingItem(this.sci, productAssignedID);
		});
	}

	@Test
	void addShoppingCartItemCreationDateOKTest(){
		this.SCIService.saveShoppingItem(this.sci, productAssignedID);

		Shoppingcartitem retrived = this.SCIService.find(shoppingCartID).get();
		assertNotNull(retrived);
		assertEquals(shoppingCartID, retrived.getShoppingcartid());
	}
	@Test
	void addProductStartSellDateTestAfter(){
		long now = System.currentTimeMillis();
		this.sci.setDatecreated(new Timestamp(now + 1000000000));
		assertThrows(InvalidParameterException.class, () -> {
			this.SCIService.saveShoppingItem(this.sci, productAssignedID);
		});
	}
}
