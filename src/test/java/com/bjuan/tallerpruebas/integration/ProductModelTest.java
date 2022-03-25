package com.bjuan.tallerpruebas.integration;
import com.bjuan.tallerpruebas.model.prod.Productmodel;
import com.bjuan.tallerpruebas.services.ProductModelService;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ProductModelTest.class)
public class ProductModelTest {
	
	// REPO Y SERVCE
	// @Mock
	// IProductModelRepository PMRepo;
	// @InjectMocks
	ProductModelService PMService;

	Productmodel pm;
	final Integer assignedID = 2103;

	@Autowired
	public ProductModelTest(ProductModelService PMService){
		this.PMService = PMService;
	}

	@BeforeEach
	void setup(){
	}


}
