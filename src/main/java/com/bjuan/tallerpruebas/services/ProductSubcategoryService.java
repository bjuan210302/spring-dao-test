package com.bjuan.tallerpruebas.services;

import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Productsubcategory;
import com.bjuan.tallerpruebas.repositories.IProductSubcategoryRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductSubcategoryService {

    private IProductSubcategoryRepository repo;
    
    public ProductSubcategoryService(IProductSubcategoryRepository repo){
        this.repo = repo;
    }

    public Optional<Productsubcategory> find(Integer id){
        return this.repo.findById(id);
    }
}