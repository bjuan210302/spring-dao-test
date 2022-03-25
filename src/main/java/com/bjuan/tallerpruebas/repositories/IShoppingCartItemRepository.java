package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartItemRepository extends CrudRepository<Shoppingcartitem, Integer>{


}
