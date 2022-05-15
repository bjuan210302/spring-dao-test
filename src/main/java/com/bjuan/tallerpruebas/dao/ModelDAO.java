package com.bjuan.tallerpruebas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bjuan.tallerpruebas.model.prod.Productmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class ModelDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Productmodel pm) {
        entityManager.persist(pm);
    }
    public void update(Productmodel pm) {
        entityManager.merge(pm);
    }
    public void delete(Productmodel pm) {
        entityManager.remove(pm);
    }
    public Productmodel findById(long pm) {
        return entityManager.find(Productmodel.class, pm);
    }
    @SuppressWarnings("unchecked")
    public List<Productmodel> findAll() {
        String jpql = "Select pm from Productmodel pm";
        return entityManager.createQuery(jpql).getResultList();
    }
}
