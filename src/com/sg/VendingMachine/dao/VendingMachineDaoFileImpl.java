/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    // creates a new HashMap saved to 
    private Map<String, Product> products = new HashMap<>();
//              <key,values>
    
      @Override
    public Product addItem(String productId, Product product) {
        Product newItem = products.put(productId, product);
	    return newItem;
    }


    //Displays all products in Hash
    @Override
    public List<Product> getAllProducts()
            throws VendingMachinePersistenceException {
        loadLibrary();
        //grabbing all values in the hash map and returns collection of the values
        //stream ie. loop through all of the values and sort 2 at a time while looping
        //grabbing the id of p1 and comparing it to p2
        //collector then collects values back into a list 
        return products.values().stream()
                .sorted((p1, p2) -> p1.getProductId().compareTo(p2.getProductId()))
                .collect(Collectors.toList());
        //return myItems;
    }

    //Gets specific product by id
    @Override
    public Product getProductById(String productId)
            throws VendingMachinePersistenceException {
        loadLibrary();
        return products.get(productId);
    }

//Not overwriting the current list just making a new one underneath
//    @Override
//    public Product updateProduct(Product product)
//            throws VendingMachinePersistenceException {
//        //Throwing an error that says incompatable types
//        Product newProduct = products.put(product.getProductId(), product);
//        writeLibrary();
//        return newProduct;
//// return products.values().stream()
////                .sorted((p1,p2) -> p1.getProductId().compareTo(p2.getProductId()))
////                .collect(Collectors.toList());
//    }
    
      @Override
    public Product updateProduct(Product product) throws VendingMachinePersistenceException {
        Product newProduct = products.put(product.getProductId(), product);
        writeLibrary();
        return newProduct;
    }

    public void loadLibrary() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            //Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("could not load Inventory data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        String[] currentTokens;

        //Goes thorugh Library_File line by line, and decodes each
        //into a product object.
        //processes while there are more lines in the file
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            String productId = (currentTokens[3]);
            String name = (currentTokens[0]);
            BigDecimal cost = new BigDecimal(currentTokens[1]);
            int quantity = Integer.parseInt(currentTokens[2]);

            Product currentProduct = new Product(name, cost, quantity, productId);
//            //set the reaining values on currentProducts manually
//            currentProduct.setCost(new BigDecimal(currentTokens[1]));
//            currentProduct.setQuantity(Integer.parseInt(currentTokens[2]));

            //put item in the map using the id as the key
            products.put(currentProduct.getProductId(), currentProduct);
        }
        scanner.close();
    }

    private void writeLibrary() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE,false));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Insufficient Funds ", e);
        }

        List<Product> ProductList = this.getAllProducts();
        for (Product currentProduct : ProductList) {
            out.println(currentProduct.getName() + DELIMITER
                    + currentProduct.getCost() + DELIMITER
                    + currentProduct.getQuantity() + DELIMITER
                    + currentProduct.getProductId());

            out.flush();

        }

        out.close();
    }

  
}
