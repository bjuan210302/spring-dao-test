package com.bjuan.tallerpruebas.services;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.prod.Productcategory;
import com.bjuan.tallerpruebas.model.prod.Productsubcategory;
import com.bjuan.tallerpruebas.repositories.IProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private IProductRepository repo;

    //Services dependencies
    private ProductCategoryService PCService;
    private ProductSubcategoryService PSCService;
    
    public ProductService(IProductRepository repo, ProductCategoryService PCService, ProductSubcategoryService PSCService){
        this.repo = repo;
        this.PCService = PCService;
        this.PSCService = PSCService;
    }

    public void save(Product p, Integer category, Integer subcategory) {
        // The subcategory gets added to the category, the product gets added to the subcategory
        // The method can't add associate a category alone.
        if (subcategory == null && category != null)
            throw new InvalidParameterException();

        if (subcategory != null){
            Optional<Productsubcategory> subcategoryObject = PSCService.find(subcategory);
            //Check if category exists
            if (subcategoryObject.isEmpty())
                throw new InvalidParameterException();

            // Cannot do but should do here
            // subcategoryObject.get().addProduct(p);
        
        }

        if (category != null){
            //Here i know that subcategory exists 
            Optional<Productcategory> categoryObject = PCService.find(category);
            //Check if category exists
            if (categoryObject.isEmpty())
                throw new InvalidParameterException();

            // Cannot do but should do here
            // Optional<Productsubcategory> subcategoryObject = psc.find(subcategory);
            // categoryObject.get().addProductsubcategory(subcategoryObject.get());
        }

        // if(p.getSellstartdate().after(p.getSellenddate()))
        //     throw new InvalidParameterException();

        if(p.getSellstartdate().equals(p.getSellenddate()))
            throw new InvalidParameterException();
        
        if(p.getSize() <= 0)
            throw new InvalidParameterException();
        
        if(p.getWeight().compareTo(new BigDecimal(0)) <= 0)
            throw new InvalidParameterException();
        
        
        this.repo.save(p);
    }

    public Optional<Product> find(Integer id){
        return this.repo.findById(id);
    }

    public Iterable<Product> findAll() {
        return repo.findAll();
    }
}