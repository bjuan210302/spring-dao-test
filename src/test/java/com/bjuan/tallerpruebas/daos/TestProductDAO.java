package com.bjuan.tallerpruebas.daos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.bjuan.tallerpruebas.TallerpruebasApplication;
import com.bjuan.tallerpruebas.dao.ProductDAO;
import com.bjuan.tallerpruebas.model.prod.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TallerpruebasApplication.class)
@Rollback(false)
public class TestProductDAO {
    
    @Autowired
    ProductDAO testedDAO;
    Product subject;

    @Autowired
    public TestProductDAO(ProductDAO testedDAO) {
        this.testedDAO = testedDAO;
    }

    public void initSubject(){
        this.subject = new Product();
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTest() {
        initSubject();
        testedDAO.save(this.subject);
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateTest() {
        initSubject();
        testedDAO.save(this.subject);
        Product retrived = testedDAO.findById(this.subject.getProductid());
        retrived.setColor("color");
        testedDAO.update(retrived);
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findByIdTest() {
        initSubject();
        testedDAO.save(this.subject);
        Product v = testedDAO.findById(this.subject.getProductid());
        assertNotNull(v);
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findAllTest() {
        initSubject();
        testedDAO.save(this.subject);
        List<Product> v = testedDAO.findAll();
        assertNotNull(v);
        assertFalse(v.isEmpty());
    }
}
