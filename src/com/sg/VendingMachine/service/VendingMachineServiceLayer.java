/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.service;

import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public interface VendingMachineServiceLayer {

    public List<Product> getItems() throws VendingMachinePersistenceException;

    public Product getItem(String productId) throws VendingMachinePersistenceException;

    public BigDecimal vendItem(Product product, BigDecimal wallet) throws InsufficientFundsException, NoInventoryException, VendingMachinePersistenceException;

    public BigDecimal depositMoney(BigDecimal money);

    public BigDecimal resetMoney(BigDecimal money);
    
    public  Product addItem(String productId, Product product);

}
