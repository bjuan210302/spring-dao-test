package com.bjuan.tallerpruebas.services;

import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Productcategory;
import com.bjuan.tallerpruebas.repositories.IProductCategoryRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

    private IProductCategoryRepository repo;
    
    public ProductCategoryService(IProductCategoryRepository repo){
        this.repo = repo;
    }

    public Optional<Productcategory> find(Integer id){
        return this.repo.findById(id);
    }
}