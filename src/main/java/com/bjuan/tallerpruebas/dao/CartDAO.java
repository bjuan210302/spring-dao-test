package com.bjuan.tallerpruebas.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class CartDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Shoppingcartitem sci) {
        entityManager.persist(sci);
    }

    public void update(Shoppingcartitem sci) {
        entityManager.merge(sci);
    }

    public void delete(Shoppingcartitem sci) {
        entityManager.remove(sci);
    }

    public Shoppingcartitem findById(long sci) {
        return entityManager.find(Shoppingcartitem.class, sci);
    }

    @SuppressWarnings("unchecked")
    public List<Shoppingcartitem> findAll() {
        String jpql = "Select sci from Shoppingcartitem sci";
        return entityManager.createQuery(jpql).getResultList();
    }

    // Permita que los carritos de compra se puedan buscar por id de producto
    @SuppressWarnings("unchecked")
    public List<Shoppingcartitem> findByProductId(Integer productid) {
        String jpql = "SELECT sci FROM Product sci WHERE sci.productid = :productid";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("productid", productid);
        return query.getResultList();
    }

    // Permita que los carritos de compra se puedan buscar por fecha de creación
    @SuppressWarnings("unchecked")
    public List<Shoppingcartitem> findByCreationDate(LocalDate datecreated) {
        String jpql = "SELECT sci FROM Product sci WHERE sci.datecreated = :datecreated";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("datecreated", datecreated);
        return query.getResultList();
    }
}
