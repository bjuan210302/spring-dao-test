package com.bjuan.tallerpruebas.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;

import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.services.ProductCostHistoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ProductModelTest.class)
public class ProductCostHistoryTest {
	
	ProductCostHistoryService PCHService;

	Productcosthistory pch;
	final Integer productHistoryAssignedID = 2016;
	final Integer associatedProductAssignedID = 2103;

	public ProductCostHistoryTest(ProductCostHistoryService PCHService){
		this.PCHService = PCHService;
	}

	@BeforeEach
	void setup(){
		this.pch = new Productcosthistory();
		
		this.pch.setId(productHistoryAssignedID);
		// Defining OK attributes	
		long now = System.currentTimeMillis();
		this.pch.setEnddate(new Timestamp(now + 1));

		// Mockito.when(this.PService.find(associatedProductAssignedID)).thenReturn(Optional.of(this.p));
		// Mockito.when(this.PCHRepo.findById(productHistoryAssignedID)).thenReturn(Optional.of(this.pch));
	}

	//ADD TEST
	@Test
	void addProdCostHistoryProductExistsTest(){
		this.PCHService.saveProductCostHistory(this.pch, associatedProductAssignedID);

		Productcosthistory retrived = this.PCHService.find(productHistoryAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productHistoryAssignedID, retrived.getId());
		assertEquals(associatedProductAssignedID, retrived.getProduct().getProductid());
	}
}
