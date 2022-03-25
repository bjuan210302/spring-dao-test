package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.prod.Productcategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductCategoryRepository extends CrudRepository<Productcategory, Integer>{


}
