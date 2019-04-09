/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.Controller;

import com.sg.VendingMachine.dao.VendingMachinePersistenceException;
import com.sg.VendingMachine.dto.Product;
import com.sg.VendingMachine.ui.UserIO;
import com.sg.VendingMachine.ui.UserIOConsoleImpl;
import com.sg.VendingMachine.dto.Change;
import com.sg.VendingMachine.ui.VendingMachineView;
import com.sg.VendingMachine.service.InsufficientFundsException;
import com.sg.VendingMachine.service.NoInventoryException;
import com.sg.VendingMachine.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
//import com.sg.DvdLibrary.ui.UserIO;
//import com.sg.DvdLibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Blake
 */
public class VendingMachineController {

    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    //created an instance of the change class to gain access to the methods in the change class
    //Is there a better way to do this? Does this defeat the purpose of using dependency injection?
    Change coins = new Change();

    //Constructor needed for dependency injection. Ensures that the controller has no idea which VendingMachineServiceLayer its using
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;

    }

    //This method will keep running as long as keepGoing is true
    public void Run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:

                        listItems();
                        break;

                    case 2:

                        viewItem();
                        break;

                    case 3:
                        listItems();
                        vend();
                        break;

                    case 4:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    //Think that this method looks a bit sloppy. Think of ways to cut down on code. Ask for tips
    private void vend() {

        boolean hasErrors = false;
        do {
            BigDecimal wallet = service.depositMoney(view.getDeposit());
            try {
                Product item = service.getItem(view.getProductChoice());
                //get back coin
                BigDecimal change = service.vendItem(item, wallet);
                view.displayChange(change);
                //make coins constructor do this
                coins.MakeChange(change);
                view.displayCoinChange(coins.giveQuarters(),coins.giveDimes(), coins.giveNickles(), coins.givePennies());
                wallet = service.resetMoney(wallet);
                hasErrors = false;
            } catch (VendingMachinePersistenceException | NoInventoryException | InsufficientFundsException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());

            }
        } while (hasErrors);
    }

    private void listItems() throws VendingMachinePersistenceException {
        view.displayDisplayAllBanner();
        List<Product> productList = service.getItems();
        view.displayProductList(productList);
    }

    private void viewItem() throws VendingMachinePersistenceException {
        view.displayDisplayProductBanner();
        String choice = view.getProductChoice();
        Product product = service.getItem(choice);
        view.displayProduct(product);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
