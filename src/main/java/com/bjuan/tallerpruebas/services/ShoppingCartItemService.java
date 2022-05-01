package com.bjuan.tallerpruebas.services;

import java.security.InvalidParameterException;
import java.util.Optional;

import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;
import com.bjuan.tallerpruebas.repositories.IShoppingCartItemRepository;

import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    private IShoppingCartItemRepository repo;
    private ProductService PService;
    
    public ShoppingCartItemService(IShoppingCartItemRepository repo, ProductService PService){
        this.repo = repo;
        this.PService = PService;
    }

    public void save(Shoppingcartitem sci, Integer productAssignedID) {
        if(productAssignedID == null)
            throw new InvalidParameterException();

        if(this.PService.find(productAssignedID).isEmpty())
            throw new InvalidParameterException();
        
        if(sci.getQuantity() <= 0)
            throw new InvalidParameterException();
        
        // if(sci.getDatecreated().after(new Timestamp(System.currentTimeMillis())))
        //     throw new InvalidParameterException();

        sci.setProductid(productAssignedID);
        this.repo.save(sci);
    }

    public Optional<Shoppingcartitem> find(Integer id){
        return this.repo.findById(id);
    }

    public Iterable<Shoppingcartitem> findAll() {
        return repo.findAll();
    }
}