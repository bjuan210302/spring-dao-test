package com.bjuan.tallerpruebas.services;

import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Productmodel;
import com.bjuan.tallerpruebas.repositories.IProductModelRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductModelService {

    private IProductModelRepository repo;
    
    public ProductModelService(IProductModelRepository repo){
        this.repo = repo;
    }

    public void save(Productmodel pm) {
        repo.save(pm);
    }

    public Optional<Productmodel> find(Integer id) {
        return this.repo.findById(id);
    }

    public Iterable<Productmodel> findAll() {
        return repo.findAll();
    }

}