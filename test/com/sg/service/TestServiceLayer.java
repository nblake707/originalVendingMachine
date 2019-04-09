package com.sg.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sg.VendingMachine.dao.VendingMachineAuditDao;
import com.sg.VendingMachine.dao.VendingMachineAuditDaoStubImpl;
import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoStubImpl;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Product;
import com.sg.VendingMachine.service.InsufficientFundsException;
import com.sg.VendingMachine.service.NoInventoryException;
import com.sg.VendingMachine.service.VendingMachineServiceLayer;
import com.sg.VendingMachine.service.VendingMachineServiceLayerImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Blake
 */
public class TestServiceLayer {

    private VendingMachineServiceLayer service;

    public TestServiceLayer() {

        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
    }

//Before testing begins, JUNIT will run this method
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //Placing code in here because this is run before each test method is run.
    @Before
    public void setUp() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        //intantiates service
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @After
    public void tearDown() {
    }

    //This was put in a try/catch bc an exception was being thrown.
    @Test
    public void getItems() {
        try {
            List<Product> Items = service.getItems();
        } catch (VendingMachinePersistenceException ex) {
            Logger.getLogger(TestServiceLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Why is there no try catch needed for this method?
    @Test
    public void getItem() throws VendingMachinePersistenceException {

        //Arrange
        Product item = new Product("cookies", new BigDecimal(2.50), 4, "06");

        //Act
        Product Items = service.getItem(item.getProductId());

        //Assert
    }

    //Unsure that I am passing in the right arguments
    @Test
    public void vendItem() throws InsufficientFundsException, NoInventoryException, VendingMachinePersistenceException {

        //Arrange
        Product item = new Product("cookies", new BigDecimal(2.50), 4, "06");
        BigDecimal wallet = new BigDecimal(5.00);
        BigDecimal newWalletValue = new BigDecimal(2.50);

        //Act
        BigDecimal vend = service.vendItem(item, wallet);

        //Assert
        assertEquals(wallet, newWalletValue);

    }

    @Test
    public void depositMoney() {

        //Arrange
        BigDecimal money = new BigDecimal("2");

        //Act
        BigDecimal deposit = service.depositMoney(money);

        //Assert
        assertEquals(money, deposit);
    }

    @Test
    public void resetMoney() {

        //Arrange
        BigDecimal money = new BigDecimal("2");
        BigDecimal zero = new BigDecimal("0");

        //Act
        BigDecimal reset = service.resetMoney(money);

        //Assert
        assertEquals(money, zero);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
