package david.software1.demo1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 The Product class represents a product in the inventory management system.
 It contains information about the product such as its ID, name, price, stock, min and max values,
 and associated parts.
 */

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private Part[] associatedParts;



    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = new Part[100];
    }


    /**
     * Gets the ID of the product.
     * @return the ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     * @param id the id to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the stock of the product.
     * @return the stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the min of the product.
     * @return the min.
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Gets the max of the product.
     * @return the max.
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Gets the associated parts of the product.
     * @return the associatedParts
     */
    public ObservableList<Part> getAssociatedPartsObservableList() {
        return FXCollections.observableArrayList(associatedParts);
    }


    /**
     * Gets the associated parts of the product.
     * @return the associatedParts
     */
    public ObservableList<Part> getAssociatedParts() {
        ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
        for (int i = 0; i < associatedParts.length; i++){
            if (associatedParts[i] != null){
                associatedPartsList.add(associatedParts[i]);
            }
        }
        System.out.println("Associated Parts List: " + associatedPartsList);
        return associatedPartsList;
    }


        /**
         * Sets the associated parts of the product.
         * @param associatedParts the associatedParts to set
         *
         */
        public void setAssociatedParts(ObservableList<Part> associatedParts){
            for (int i = 0; i < associatedParts.size(); i++){
                this.associatedParts[i] = associatedParts.get(i);
            }


        }

        /**
         * Adds an associated part to the product.
         * @param part the associatedPart to add.
         *
         */
    public Part addAssociatedPart(Part part){
        for (int i = 0; i < associatedParts.length; i++){
            if (associatedParts[i] == null){
                associatedParts[i] = part;
                return part;
            }
        }
        return null;
    }


}

