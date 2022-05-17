package com.bjuan.tallerpruebas.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import com.bjuan.tallerpruebas.TallerpruebasApplication;
import com.bjuan.tallerpruebas.dao.AdditionalQueries;
import com.bjuan.tallerpruebas.dao.CartDAO;
import com.bjuan.tallerpruebas.dao.ProductDAO;
import com.bjuan.tallerpruebas.model.prod.Product;
import com.bjuan.tallerpruebas.model.prod.Productcosthistory;
import com.bjuan.tallerpruebas.model.prod.Productsubcategory;
import com.bjuan.tallerpruebas.model.sales.Shoppingcartitem;

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
public class TestAdditionalQueries {
    
    @Autowired
    AdditionalQueries testedDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    public TestAdditionalQueries(AdditionalQueries testedDAO, ProductDAO productDAO, CartDAO cartDAO) {
        this.testedDAO = testedDAO;
        this.productDAO = productDAO;
        this.cartDAO = cartDAO;
    }

    public Product generateProduct(String pname){
        Product p = new Product();
        p.setName(pname);
        p.setProductsubcategory(new Productsubcategory());
        return p;
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTest() {
        Product p1 = generateProduct("p1");
        productDAO.save(p1); // subcategory 1
        Product p2 = generateProduct("p2");
        productDAO.save(p2); // subcategory 2
        Product p3 = generateProduct("p3");
        productDAO.save(p3); // subcategory 3
        
        LocalDate today = LocalDate.now();

        // Un elemento
        Shoppingcartitem c1 = new Shoppingcartitem();
        c1.setDatecreated(today);
        c1.setProductid(p1.getProductid());
        cartDAO.save(c1);

        List<Product> retrived = testedDAO.findProductPerCart(today);
        assertEquals(1, retrived.size());
        assertEquals(p1.getName(), retrived.get(0).getName());

        // Multiples elementos
        Shoppingcartitem c2 = new Shoppingcartitem();
        c2.setDatecreated(today);
        c2.setProductid(p2.getProductid());
        cartDAO.save(c2);
        Shoppingcartitem c3 = new Shoppingcartitem();
        c3.setDatecreated(today);
        c3.setProductid(p3.getProductid());
        cartDAO.save(c3);

        retrived = testedDAO.findProductPerCart(today);
        assertEquals(3, retrived.size());
        // Ordenados por subcategoria
        assertEquals(p1.getName(), retrived.get(0).getName());
        assertEquals(p2.getName(), retrived.get(1).getName());
        assertEquals(p3.getName(), retrived.get(2).getName());
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void findUpdatedProductsTestNone() {
        Product p1 = generateProduct("p1");
        productDAO.save(p1);
        Product p2 = generateProduct("p2");
        productDAO.save(p2);
        Product p3 = generateProduct("p3");
        productDAO.save(p3);

        // Ningun producto tiene dos o mas actuliazaciones
        List<Product> retrived = testedDAO.findUpdatedProducts();
        assertEquals(0, retrived.size());

        p1.addProductcosthistory(new Productcosthistory());
        productDAO.update(p1);
        p2.addProductcosthistory(new Productcosthistory());
        productDAO.update(p2);
        p3.addProductcosthistory(new Productcosthistory());
        productDAO.update(p3);
        retrived = testedDAO.findUpdatedProducts();
        assertEquals(0, retrived.size());

        // Un producto tiene dos o mas actuliazaciones
        p1.addProductcosthistory(new Productcosthistory());
        productDAO.update(p1);
        retrived = testedDAO.findUpdatedProducts();
        assertEquals(1, retrived.size());

        // Mulyiples productos tienen dos o mas de dos actuliazaciones
        p1.addProductcosthistory(new Productcosthistory());
        productDAO.update(p1);
        p2.addProductcosthistory(new Productcosthistory());
        productDAO.update(p2);
        p3.addProductcosthistory(new Productcosthistory());
        productDAO.update(p3);
        retrived = testedDAO.findUpdatedProducts();
        assertEquals(3, retrived.size());
    }
}
