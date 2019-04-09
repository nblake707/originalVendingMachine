/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.service;

import com.sg.VendingMachine.dao.VendingMachineAuditDao;
import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blake
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private final VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    
    
    private BigDecimal myMoney = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
      
    }


    @Override
    public List<Product> getItems() throws VendingMachinePersistenceException {
        return dao.getAllProducts();
    }

    @Override
    public Product getItem(String productId) throws VendingMachinePersistenceException {
        return dao.getProductById(productId);

    }

    
//    //Created a method called vendItem that takes in a variable of type product and a variable of type bigdecimal
//        //if the amount of money in the wallet is greater than the cost of the item and the quantity of the item and is more than 0 then it will set the quanitiy of the item to 
//        //one less and set the amount of money in the wallet to the original price minus the cost of the product
//        //also returns the final value of the wallet variable. 
    @Override
    public BigDecimal vendItem(Product product, BigDecimal wallet) throws NoInventoryException, InsufficientFundsException, VendingMachinePersistenceException {
        if (wallet.floatValue() > product.getCost().floatValue()) {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                
                //Want to include update method here but it isn't overwriting the current list only creating a new list everytime
                dao.updateProduct(product);
                try {
                    auditDao.writeAuditEntry("Item dipensed");
                } catch (VendingMachinePersistenceException ex) {
                    Logger.getLogger(VendingMachineServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return wallet.subtract(product.getCost());
            } else {
                throw new NoInventoryException("There no item in this vending machine");
            }
        } else {
            throw new InsufficientFundsException("You do not have the funds to buy this product.");
        }

    }

    @Override
    public BigDecimal depositMoney(BigDecimal money) {
        myMoney = myMoney.add(money);
        return myMoney;
    }

    @Override
    public BigDecimal resetMoney(BigDecimal money) {
        myMoney = myMoney.subtract(money);
        return myMoney;
    }

    @Override
    public Product addItem(String productId, Product product) {
       Product newItem = dao.addItem(productId, product);
	    return newItem;
    }

}
