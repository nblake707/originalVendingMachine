/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachineDao.TestDao;

import com.sg.VendingMachine.dto.Product;
import java.math.BigDecimal;

/**
 *
 * @author Blake
 */
public class TestHelper {

    public static Product createProduct() {
        Product product = new Product("cookies", new BigDecimal(2.50), 4, "06");
        return product;
    }

    public static Product createAnotherProduct() {
        Product product = new Product("Ham", new BigDecimal(5.50), 3, "07");
        return product;
    }
    
      public static Product createJekChicken() {
        Product product = new Product("JerkChicken", new BigDecimal(10.50), 2, "08");
        return product;
      }

}
