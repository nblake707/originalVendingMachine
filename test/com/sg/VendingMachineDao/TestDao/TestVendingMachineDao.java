/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachineDao.TestDao;

import com.sg.VendingMachine.dao.VendingMachineDaoStubImpl;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Product;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Blake
 */
public class TestVendingMachineDao {

    VendingMachineDaoStubImpl dao;

    public TestVendingMachineDao() {
        this.dao = dao;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao = new VendingMachineDaoStubImpl();
    }

    @After
    public void tearDown() {
    }
    
 
    
//    @Test
//    public void addItem(){
//       Product product = TestHelper.createJekChicken();
//        
//        //Act
//        Product newproduct = dao.addItem("08", product);
//        
//        //Assert
//        assertEquals(product, newproduct);
//    }

    @Test
    public void ReturnProductList() throws VendingMachinePersistenceException {

        //AAA pattern
        //Arranging
        Product product = TestHelper.createProduct();
        dao.addItem("001", product);

        //Act
        List<Product> productList = dao.getAllProducts();

        //Assert
        assertEquals(1, productList.size());
        assertEquals(product, productList.get(0));
    }

    @Test
    public void ReturnProductById() throws VendingMachinePersistenceException {
        //Arrange
        Product product = TestHelper.createProduct();
        dao.addItem("001", product);

        //Act
        Product getProduct = dao.getProductById("001");

        //Assert
        assertEquals(product, getProduct);

    }

    @Test
    public void updateProduct() throws VendingMachinePersistenceException {

        //Arrange
        Product product = TestHelper.createProduct();
        Product newProduct = TestHelper.createAnotherProduct();
        dao.addItem("001", product);
        dao.addItem("002", newProduct);

        //Act
        Product getProduct = dao.updateProduct(product);

        //Assert
        assertEquals(product, getProduct);
    }
}
