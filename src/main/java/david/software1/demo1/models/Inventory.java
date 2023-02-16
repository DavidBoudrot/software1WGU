package david.software1.demo1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 The Product class represents a product in the inventory management system.
 it contains both the products and their associated parts.
 */

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static StringBuilder searchPart = new StringBuilder();
    private static StringBuilder searchProduct = new StringBuilder();
    private static int partIDCount = 0;
    private static int productIDCount = 0;




    /**
     * adds a part to the inventory.
     */
    public static void addPart(Part newPart) {

        allParts.add(newPart);
        partIDCount++;


    }

    /**
     * adds a product to the inventory.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
        productIDCount++;
    }

    /**
     * looks up a part by ID.
     */
    public static Part lookupPart(int partID) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partID) {
                return allParts.get(i);
            }
        }
        return null;
    }

    /**
     * looks up a part by name.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().contains(partName)) {
                foundParts.add(allParts.get(i));
            }
        }
        return foundParts;
    }


    /**
     * looks up a product by ID.
     */
    public static Product lookupProduct(int productID) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productID) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    /**
     * looks up a product by name.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().contains(productName)) {
                foundProducts.add(allProducts.get(i));
            }
        }
        return foundProducts;
    }


    /**
     * checks if a part is in use.
     */
    public static boolean isPartInUse(Part part) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getAssociatedParts().contains(part)) {
                return true;
            }
        }
        return false;
    }




    /**
     * updates a part.
     */

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * updates a product.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * deletes a part.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * deletes a product.
     */

    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }


    /**
     * gets all parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * gets all products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    /**
     * gets the max partID / amount of parts - 1.
     */
    public static int getPartIDCount() {
        return partIDCount;
    }


    /**
     * gets the max productID / amount of products - 1.
     */
    public static int getProductIDCount() {
        return productIDCount;
    }


    /**
     * checks if the inventory is empty.
     */
    public static Boolean isEmpty() {
        if (allParts.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

}

