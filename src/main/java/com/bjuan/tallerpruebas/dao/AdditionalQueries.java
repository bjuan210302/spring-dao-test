package com.bjuan.tallerpruebas.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bjuan.tallerpruebas.model.prod.Product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class AdditionalQueries {
    @PersistenceContext
    private EntityManager entityManager;

    // Lo(s) productos (s) con sus datos y suma de cantidades en carritos de compra, ordenados por subcategoría.
    // Recibe como parámetro una fecha de creación de los carritos y retorna todos los productos que cumplen con tener
    // al menos un producto en el carrito para esa fecha.
    @SuppressWarnings("unchecked")
    public List<Product> findProductPerCart(LocalDate datecreated) {
        String jpql = "SELECT p FROM Product p, Shoppingcartitem sci WHERE sci.datecreated = :datecreated AND sci.productid = p.productid ORDER BY p.productsubcategory.productsubcategory ASC";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("datecreated", datecreated);
        return query.getResultList();
    }

    // Mostrar el listado productos para los productos que tienen al menos dos actualizaciones de precios históricos.
    @SuppressWarnings("unchecked")
    public List<Product> findUpdatedProducts() {
        String jpql = "SELECT p FROM Product p WHERE size(p.productcosthistories) >= 2 ORDER BY size(p.productcosthistories) ASC";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}
