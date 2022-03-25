package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.prod.Productsubcategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductSubcategoryRepository extends CrudRepository<Productsubcategory, Integer>{


}
