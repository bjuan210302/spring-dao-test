package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.prod.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product, Integer>{


}
