package com.bjuan.tallerpruebas.services;

import java.security.InvalidParameterException;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.repositories.IProductCostHistoryRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductCostHistoryService {

    private IProductCostHistoryRepository repo;
    private ProductService PService;
    
    public ProductCostHistoryService(IProductCostHistoryRepository repo, ProductService PService){
        this.repo = repo;
        this.PService = PService;
    }

    public void save(Productcosthistory pch, Integer associatedProduct) {
        if(associatedProduct == null)
            throw new InvalidParameterException();

        if(this.PService.find(associatedProduct).isEmpty())
            throw new InvalidParameterException();
        
        // Product p = this.PService.find(associatedProduct).get();
        // if(p.getSellstartdate().isAfter(pch.getEnddate()))
        //     throw new InvalidParameterException();
        
        // if(p.getListprice().compareTo(new BigDecimal(0)) <= 0)
        //     throw new InvalidParameterException();

        this.repo.save(pch);
    }

    public Optional<Productcosthistory> find(Integer id){
        return this.repo.findById(id);
    }

    public Iterable<Productcosthistory> findAll() {
        return repo.findAll();
    }
}