package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.prod.Productmodel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductModelRepository extends CrudRepository<Productmodel, Integer>{

}
