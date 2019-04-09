/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Blake
 */
public class Product {

    private String name;
    private BigDecimal cost;
    private int quantity;
//    private Integer productId;
    private String productId;

    public Product(String name, BigDecimal cost, int quantity, String productId) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.productId = productId;

    }

    public void setName(String name) {
        this.name = name;
    }
    
      public String getName() {
        return name;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }


    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
