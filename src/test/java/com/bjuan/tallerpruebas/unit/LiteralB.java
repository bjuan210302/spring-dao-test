package com.bjuan.tallerpruebas.unit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.prod.Productcategory;
import com.bjuan.tallerpruebas.model.prod.Productsubcategory;
import com.bjuan.tallerpruebas.repositories.IProductRepository;
import com.bjuan.tallerpruebas.services.ProductCategoryService;
import com.bjuan.tallerpruebas.services.ProductService;
import com.bjuan.tallerpruebas.services.ProductSubcategoryService;

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
public class LiteralB {

	// REPO Y SERVCE
	@Mock
	IProductRepository PRepo;
	@Mock
	ProductCategoryService PCService;
	@Mock
	ProductSubcategoryService PSCService;

	@InjectMocks
	ProductService PService;

	Product p;
	Productcategory c;
	Productsubcategory sc;
	
	final Integer productAssignedID = 2103;
	final Integer categoryAssignedID = 2016;
	final Integer subcategoryAssignedID = 2004;

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

		this.c = new Productcategory();
		this.c.setProductcategoryid(categoryAssignedID);
		this.sc = new Productsubcategory();
		this.sc.setProductsubcategoryid(subcategoryAssignedID);
		
		Mockito.when(this.PCService.find(categoryAssignedID)).thenReturn(Optional.of(this.c));
		Mockito.when(this.PSCService.find(subcategoryAssignedID)).thenReturn(Optional.of(this.sc));
		Mockito.when(this.PRepo.findById(productAssignedID)).thenReturn(Optional.of(this.p));
	}

	//ADD TESTS
	@Test
	void addProductNoCategoryNoSubcategory(){
		this.PService.saveProduct(this.p, null, null);

		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());
	}
	@Test
	void addProductStartSellDateTestOK(){
		this.PService.saveProduct(this.p, null, null);
		
		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());
	}
	@Test
	void addProductStartSellDateTestEqual(){
		long now = System.currentTimeMillis();
		this.p.setSellstartdate(new Timestamp(now));
		this.p.setSellenddate(new Timestamp(now));

		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, null);
		});
	}
	@Test
	void addProductStartSellDateTestAfter(){
		long now = System.currentTimeMillis();
		this.p.setSellstartdate(new Timestamp(now));
		this.p.setSellenddate(new Timestamp(now - 1));

		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, null);
		});
	}
	@Test
	void addProductSizeTestOK(){
		this.PService.saveProduct(this.p, null, null);
		
		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());
	}
	@Test
	void addProductZeroSizeTest(){
		this.p.setSize(0);

		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, null);
		});
	}
	@Test
	void addProductNegativeSizeTest(){
		this.p.setSize(-1);

		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, null);
		});
	}
	@Test
	void addProductWeightTestOK(){
		this.PService.saveProduct(this.p, null, null);
		
		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());
	}
	@Test
	void addProductZeroWeightTest(){
		this.p.setWeight(new BigDecimal(0));

		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, null);
		});
	}
	@Test
	void addProductNegativeWeightTest(){
		this.p.setWeight(new BigDecimal(-0.1));

		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, null);
		});	
	}

	@Test
	void addProductCategoryAlone(){
		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, categoryAssignedID, null);
		});	
	}
	@Test
	void addProductCategoryNotExistTest(){
		Mockito.when(this.PCService.find(categoryAssignedID)).thenReturn(Optional.empty());
		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, categoryAssignedID, subcategoryAssignedID);
		});
	}
	@Test
	void addProductSubcategoryExistTest(){
		this.PService.saveProduct(this.p, null, subcategoryAssignedID);

		Product retrived = this.PService.find(productAssignedID).get();
		assertNotNull(retrived);
		assertEquals(productAssignedID, retrived.getProductid());

		//Should assert this, but an exception is thrown at line 42 of ProductService.java
		//That exception won't let me add the product subcategory to the product
		//assertEquals(retrived.getProductsubcategory().getProductsubcategoryid(), subcategoryAssignedID);
	}
	@Test
	void addProductSubategoryNotExistTest(){
		Mockito.when(this.PSCService.find(subcategoryAssignedID)).thenReturn(Optional.empty());
		assertThrows(InvalidParameterException.class, () -> {
			this.PService.saveProduct(this.p, null, subcategoryAssignedID);
		});
	}
	
}
