/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Blake
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    Product onlyProduct;
    private List<Product> productList = new ArrayList<>();
 
    public VendingMachineDaoStubImpl() {
        onlyProduct = new Product("cookies", new BigDecimal(2.50), 4, "06");
        productList.add(onlyProduct);
    }

    @Override
    public List<Product> getAllProducts() throws VendingMachinePersistenceException {
        return productList;
    }

    @Override
    public Product getProductById(String productId) throws VendingMachinePersistenceException {
        if (productId.equals(onlyProduct.getProductId())) {
            return onlyProduct;
        } else { 
            return null;
        }
    }
    

//Had trouble with this method. 

    @Override
    public Product updateProduct(Product productId) throws VendingMachinePersistenceException {
         if (productId.equals(onlyProduct.getProductId())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product addItem(String productId, Product product) {
    
	    return product;
    }
    

}
