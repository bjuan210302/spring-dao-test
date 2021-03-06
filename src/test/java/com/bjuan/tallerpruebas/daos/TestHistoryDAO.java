package com.bjuan.tallerpruebas.daos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import com.bjuan.tallerpruebas.TallerpruebasApplication;
import com.bjuan.tallerpruebas.dao.HistoryDAO;
import com.bjuan.tallerpruebas.dao.ProductDAO;
import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.prod.Productcosthistory;

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
public class TestHistoryDAO {
    
    @Autowired
    HistoryDAO testedDAO;

    @Autowired
    ProductDAO productDAO;

    Productcosthistory subject;

    @Autowired
    public TestHistoryDAO(HistoryDAO testedDAO, ProductDAO productDAO) {
        this.testedDAO = testedDAO;
        this.productDAO = productDAO;
    }

    public void initSubject(){
        this.subject = new Productcosthistory();
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
        Productcosthistory retrived = testedDAO.findById(this.subject.getProductcosthitoryid());
        retrived.setStandardcost(new BigDecimal(100));
        testedDAO.update(retrived);
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findByIdTest() {
        initSubject();
        testedDAO.save(this.subject);
        Productcosthistory v = testedDAO.findById(this.subject.getProductcosthitoryid());
        assertNotNull(v);
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findAllTest() {
        initSubject();
        testedDAO.save(this.subject);
        List<Productcosthistory> v = testedDAO.findAll();
        assertNotNull(v);
        assertFalse(v.isEmpty());
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findByProductIdTest() {
        initSubject();
        Product p = new Product();
        this.subject.setProduct(p);
        testedDAO.save(this.subject);
        List<Productcosthistory> v = testedDAO.findByProductId(p.getProductid());
        assertNotNull(v);
        assertFalse(v.isEmpty());
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findByCreationDateTest() {
        initSubject();
        Product p = new Product();
        p.setListprice(new BigDecimal(1));
        this.subject.setProduct(p);
        testedDAO.save(this.subject);
        List<Productcosthistory> v = testedDAO.findByListPrice(p.getListprice());
        assertNotNull(v);
        assertFalse(v.isEmpty());
    }
}
