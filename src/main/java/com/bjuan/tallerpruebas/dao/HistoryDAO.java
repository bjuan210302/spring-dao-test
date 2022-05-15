package com.bjuan.tallerpruebas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bjuan.tallerpruebas.model.prod.Productcosthistory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class HistoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Productcosthistory pch) {
        entityManager.persist(pch);
    }

    public void update(Productcosthistory pch) {
        entityManager.merge(pch);
    }

    public void delete(Productcosthistory pch) {
        entityManager.remove(pch);
    }

    public Productcosthistory findById(Integer pch) {
        return entityManager.find(Productcosthistory.class, pch);
    }

    @SuppressWarnings("unchecked")
    public List<Productcosthistory> findAll() {
        String jpql = "Select pch from Productcosthistory pch";
        return entityManager.createQuery(jpql).getResultList();
    }

    // Permita que los precios históricos de productos puedan buscarse por el id del
    // producto
    @SuppressWarnings("unchecked")
    public List<Productcosthistory> findByProductId(Integer productid) {
        String jpql = "SELECT pch FROM Product pch WHERE pch.product.productid = :productid";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("productid", productid);
        return query.getResultList();
    }

    // Permita que los precios históricos de productos puedan buscarse por el precio
    // de lista
    @SuppressWarnings("unchecked")
    public List<Productcosthistory> findByListPrice(BigDecimal listprice) {
        String jpql = "SELECT pch FROM Product pch WHERE pch.product.listprice = :listprice";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("listprice", listprice);
        return query.getResultList();
    }
}
