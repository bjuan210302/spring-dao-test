package com.bjuan.tallerpruebas.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.services.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ProductModelTest.class)
public class ProductTest {

	ProductService PService;

	Product p;

	final Integer productAssignedID = 2103;
	final Integer categoryAssignedID = 2016;
	final Integer subcategoryAssignedID = 2004;

	public ProductTest(ProductService PService){
		this.PService = PService; 
	}

	@BeforeEach
	void setup(){
		this.p = new Product();
		//Defining OK attributes
		this.p.setProductid(this.productAssignedID);
		long now = System.currentTimeMillis();
		this.p.setSellstartdate(new Timestamp(now));
		this.p.setSellenddate(new Timestamp(now + 1));
		this.p.setSize(1);
		this.p.setWeight(new BigDecimal(0.1));
		
		// Mockito.when(this.PCService.find(categoryAssignedID)).thenReturn(Optional.of(this.c));
		// Mockito.when(this.PSCService.find(subcategoryAssignedID)).thenReturn(Optional.of(this.sc));
		// Mockito.when(this.PRepo.findById(productAssignedID)).thenReturn(Optional.of(this.p));
	}

	//ADD TESTS
	@Test
	void addProductSubcategoryExistTest(){
		this.PService.saveProduct(this.p, null, subcategoryAssignedID);

		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());

		assertEquals(retrived.getProductsubcategory().getProductsubcategoryid(), subcategoryAssignedID);
	}

	@Test
	void addProductCategoryExistTest(){
		this.PService.saveProduct(this.p, categoryAssignedID, subcategoryAssignedID);

		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());

		assertEquals(retrived.getProductsubcategory().getProductsubcategoryid(), subcategoryAssignedID);
		assertEquals(
			retrived.getProductsubcategory().getProductcategory().getProductcategoryid(),
			categoryAssignedID);
	}
	
}
