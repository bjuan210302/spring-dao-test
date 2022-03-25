package com.bjuan.tallerpruebas.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Productmodel;
import com.bjuan.tallerpruebas.repositories.IProductModelRepository;
import com.bjuan.tallerpruebas.services.ProductModelService;

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
public class LiteralA {
	
	// REPO Y SERVCE
	@Mock
	IProductModelRepository PMRepo;
	@InjectMocks
	ProductModelService PMService;

	Productmodel pm;
	final Integer assignedID = 2103;

	@BeforeEach
	void setupProductModel(){
		this.pm = new Productmodel();
		this.pm.setProductmodelid(this.assignedID);

		//Defining OK attributes
		this.pm.setInstructions("This contains at least five characters");
		pm.setName("This contains at least five characters");

		Mockito.when(this.PMRepo.findById(assignedID)).thenReturn(Optional.of(this.pm));
	}

	//ADD TESTS
		//NAME
	@Test
	void addNoNameTest() {
		pm.setName(null);
		assertThrows(InvalidParameterException.class, () -> {
			PMService.saveProductModel(pm);
		});
	}
	@Test
	void addShortNameTest() {
		pm.setName("Four");
		assertThrows(InvalidParameterException.class, () -> {
			PMService.saveProductModel(pm);
		});
	}
	@Test
	void addNameOKTest() {
		PMService.saveProductModel(pm);
		
		Productmodel retrivedPM = PMService.find(this.assignedID).get();
		assertNotNull(retrivedPM);
		assertEquals(pm.getName(), retrivedPM.getName());
	}
		//DESC
	@Test
	void addNoDescTest() {
		this.pm.setInstructions(null);
		assertThrows(InvalidParameterException.class, () -> {
			PMService.saveProductModel(pm);
		});
	}
	@Test
	void addShortDescTest() {
		this.pm.setInstructions("Four");
		assertThrows(InvalidParameterException.class, () -> {
			PMService.saveProductModel(pm);
		});
	}
	@Test
	void addDescOKTest() {
		PMService.saveProductModel(pm);
		
		Productmodel retrivedPM = PMService.find(this.assignedID).get(); 
		assertNotNull(retrivedPM);
		assertEquals(pm.getInstructions(), retrivedPM.getInstructions());
	}

}
