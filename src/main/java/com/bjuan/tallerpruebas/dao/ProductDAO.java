package com.bjuan.tallerpruebas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bjuan.tallerpruebas.model.prod.Product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Product p) {
        entityManager.persist(p);
    }

    public void update(Product p) {
        entityManager.merge(p);
    }

    public void delete(Product p) {
        entityManager.remove(p);
    }

    public Product findById(Integer p) {
        return entityManager.find(Product.class, p);
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        String jpql = "Select p from Product p";
        return entityManager.createQuery(jpql).getResultList();
    }

    // Permita que los productos puedan buscarse por el id de la subcategoría
    @SuppressWarnings("unchecked")
    public List<Product> findBySubcategoryId(Integer subcategory) {
        String jpql = "SELECT p FROM Product p WHERE p.productsubcategory.subcategory = :subcategory";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("subcategory", subcategory);
        return query.getResultList();
    }

    // Permita que los productos puedan buscarse por el modelo del product (name)
    @SuppressWarnings("unchecked")
    public List<Product> findByModelName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.productmodel.name = :name";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("name", name);
        return query.getResultList();
    }

    // Permita que los productos puedan buscarse por el codigo de la unidad de peso
    @SuppressWarnings("unchecked")
    public List<Product> findByUnitmeasureCode(String unitmeasurecode) {
        String jpql = "SELECT p FROM Product p WHERE p.unitmeasure2.unitmeasurecode = :unitmeasurecode";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("unitmeasurecode", unitmeasurecode);
        return query.getResultList();
    }
}
