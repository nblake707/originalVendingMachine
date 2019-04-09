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
public class Change {

    private BigDecimal pennies = new BigDecimal("0.01");
    private BigDecimal nickels = new BigDecimal("0.05");
    private BigDecimal dimes = new BigDecimal("0.10");
    private BigDecimal quarters = new BigDecimal("0.25");

    private int coinQuarters = 0, CoinDimes = 0, coinNickels = 0, coinPennies = 0;



 
//make this a constructor
    public void MakeChange(BigDecimal change) {
//           BigDecimal pennyChange = change.divide(pennies);

        while (change.floatValue() > 0) {

            if (change.floatValue() >= .25) {
                change = change.subtract(quarters);
                coinQuarters++;
            } else if (change.floatValue() >= .10) {
                change = change.subtract(dimes);
                CoinDimes++;
            } else if (change.floatValue() >= .5) {
                change = change.subtract(nickels);
                coinNickels++;
            } else {
                change = change.subtract(pennies);
                coinPennies++;
            }

        }
    }

    public int giveQuarters() {

        return coinQuarters;

    }

    public int giveDimes() {
        return CoinDimes;

    }

    public int giveNickles() {
        return coinNickels;

    }

    public int givePennies() {
        return coinPennies;

    }
}
