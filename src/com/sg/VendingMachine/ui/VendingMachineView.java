/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.ui;

import com.sg.VendingMachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List All Items");
        io.print("2. Display Item Information");
        io.print("3. Vend Item");
        io.print("4. Exit");
        return io.readInt("Please select from the above choices.", 1, 4);
    }

    public void displayProductList(List<Product> productList) {
        for (Product currentProduct : productList) {
            io.print(currentProduct.getProductId() + " / "
                    + currentProduct.getName() + ": "
                    + currentProduct.getCost() + " / "
                    + currentProduct.getQuantity());
        }

        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== All Products ===");
    }

    //Display single dvd
    public void displayDisplayProductBanner() {
        io.print("=== Display a Product ===");
    }

    public String getProductChoice() {
        return io.readString("Please enter Item Id.");
    }

    public void displayProduct(Product product) {
        if (product != null) {
            io.print(product.getName() + ": "
                    + product.getCost() + " / "
                    + product.getQuantity());
        } else {
            io.print("No such Product.");
        }
        io.readString("Please hit enter to continue.");
    }

    //Vend Item
    public void displayVendProductBanner() {
        io.print("=== Vend Product ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public BigDecimal getDeposit() {
        BigDecimal deposit = new BigDecimal(io.readString("Please Deposit Money."));
        return deposit;

    }

    public BigDecimal displayChange(BigDecimal change) {
        BigDecimal realChange = io.readBigDecimal(change, "Your Change is: ");
        return realChange;
    }

    public void displayCoinChange(int quarters, int dimes, int nickels, int pennies) {

        io.readInt(quarters, "You have: " + quarters + " Quarters ");
        io.readInt(dimes, "You have: " + dimes + " Dimes ");
        io.readInt(nickels, "You have: " + nickels + " Nickels ");
        io.readInt(pennies, "You have: " + pennies + " Pennies ");
        io.readString("Product successfully purchased. Please hit enter to continue.");

    }

}
