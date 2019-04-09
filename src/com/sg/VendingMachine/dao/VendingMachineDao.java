/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Product;
import java.util.List;

/**
 *
 * @author Blake
 */
public interface VendingMachineDao {
    
    Product addItem(String productId, Product product);


    List<Product> getAllProducts()
            throws VendingMachinePersistenceException;

    Product getProductById(String productId)
            throws VendingMachinePersistenceException;

    Product updateProduct(Product product)
            throws VendingMachinePersistenceException;

}
