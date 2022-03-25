package com.bjuan.tallerpruebas.unit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.repositories.IProductCostHistoryRepository;
import com.bjuan.tallerpruebas.services.ProductCostHistoryService;
import com.bjuan.tallerpruebas.services.ProductService;

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
public class LiteralD {
	
	@Mock
	IProductCostHistoryRepository PCHRepo;
	@Mock
	ProductService PService;
	
	@InjectMocks
	ProductCostHistoryService PCHService;

	Productcosthistory pch;
	Product p;
	final Integer productHistoryAssignedID = 2016;
	final Integer associatedProductAssignedID = 2103;

	@BeforeEach
	void setupProductModel(){
		this.pch = new Productcosthistory();
		this.p = new Product();
		
		this.pch.setId(productHistoryAssignedID);
		this.p.setProductid(associatedProductAssignedID);
		// Defining OK attributes	
		long now = System.currentTimeMillis();
		this.p.setListprice(new BigDecimal(1.0));
		this.p.setSellstartdate(new Timestamp(now));
		this.pch.setEnddate(new Timestamp(now + 1));

		Mockito.when(this.PService.find(associatedProductAssignedID)).thenReturn(Optional.of(this.p));
		Mockito.when(this.PCHRepo.findById(productHistoryAssignedID)).thenReturn(Optional.of(this.pch));
	}

	//ADD TEST
	@Test
	void addProdCostHistoryProductExistsTest(){
		this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);

		Productcosthistory retrived = this.PCHService.find(productHistoryAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productHistoryAssignedID, retrived.getId());
	}
	@Test
	void addProdCostHistoryProductNotExistsTest(){
		Mockito.when(this.PService.find(associatedProductAssignedID)).thenReturn(Optional.empty());
		assertThrows(InvalidParameterException.class, () -> {
			this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);
		});
	}
	@Test
	void addShoppingCartItemProductNullTest(){
		assertThrows(InvalidParameterException.class, () -> {
			this.PCHService.saveProductCostHistory(this.pch, null);
		});
	}


	@Test
	void addProdCostHistoryCreationDateOK(){
		this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);

		Productcosthistory retrived = this.PCHService.find(productHistoryAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productHistoryAssignedID, retrived.getId());
	}
	@Test
	void addProdCostHistoryCreationDateAfterTest(){
		long now = System.currentTimeMillis();
		this.p.setSellstartdate(new Timestamp(now));
		this.pch.setEnddate(new Timestamp(now - 1));

		assertThrows(InvalidParameterException.class, () -> {
			this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);
		});
	}

	@Test
	void addProdCostHistoryPriceTestOKTest(){
		this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);

		Productcosthistory retrived = this.PCHService.find(productHistoryAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productHistoryAssignedID, retrived.getId());
	}
	@Test
	void addProdCostHistoryPriceTestZeroTest(){
		this.p.setListprice(new BigDecimal(0));
		assertThrows(InvalidParameterException.class, () -> {
			this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);
		});
	}
	@Test
	void addProdCostHistoryPriceTestBelowZeroTest(){
		this.p.setListprice(new BigDecimal(-1.0));
		assertThrows(InvalidParameterException.class, () -> {
			this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);
		});
	}
}
