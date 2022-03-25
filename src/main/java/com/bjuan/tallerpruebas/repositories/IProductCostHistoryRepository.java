package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.prod.Productcosthistory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductCostHistoryRepository extends CrudRepository<Productcosthistory, Integer>{
    
}
