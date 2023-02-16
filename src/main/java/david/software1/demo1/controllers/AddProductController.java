package david.software1.demo1.controllers;

import david.software1.demo1.models.Alerts;
import david.software1.demo1.models.Inventory;
import david.software1.demo1.models.Part;
import david.software1.demo1.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


/**
 * The ModifyProductController class controls the Modify Product view.
 */
public class AddProductController {

    @FXML
    private Button AddProductAddButton;

    @FXML
    private TableView<Part> PartsTableView;
    @FXML
    private TableColumn<Part, Integer> PartsIDColumn;

    @FXML
    private TableColumn<Part, String> PartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> PartsInventoryColumn;
    @FXML
    private TableColumn<Part, Double> PartsCostColumn;


    //Associated Parts Table
    @FXML
    private TableView<Part> AssociatedPartsTableView;
    @FXML
    private TableColumn<Product, Integer> AssociatedPartsIDColumn;
    @FXML
    private TableColumn<Product, String> AssociatedPartsNameColumn;
    @FXML
    private TableColumn<Product, Integer> AssociatedPartsInventoryColumn;
    @FXML
    private TableColumn<Product, Double> AssociatedPartsCostColumn;

    @FXML
    private Button AddProductCancelButton;

    @FXML
    private TextField AddProductIdTextPrompt;

    @FXML
    private TextField AddProductInvTextPrompt;

    @FXML
    private TextField AddProductMaxTextPrompt;

    @FXML
    private TextField AddProductMinTextPrompt;

    @FXML
    private TextField AddProductNameTextPrompt;

    @FXML
    private TextField AddProductPriceTextPrompt;

    @FXML
    private Button AddProductRemoveAssociatedPartButton;

    @FXML
    private Button AddProductSaveButton;

    @FXML
    private TextField AddProductSearchTextPrompt;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private ObservableList<Part> originalParts = FXCollections.observableArrayList();


    /**
     * initialize method is called when the view is loaded.
     */
    @FXML
    void initialize() {
        //create id for new product
        AddProductIdTextPrompt.setText(String.valueOf(getNewID()));
        //make id text uneditable
        AddProductIdTextPrompt.setEditable(false);

        originalParts = Inventory.getAllParts();


        //create the columns for the table
        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        PartsTableView.setItems(Inventory.getAllParts());


        //Columns and Table for associated parts
        AssociatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        AssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        AssociatedPartsTableView.setItems(associatedParts);


        updateAssociatedPartTableView();
        updatePartTableView();


    }

    /**
     * gets the next available id for a new product
     */
    private int getNewID() {
        int newID = 0;
        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            if (Inventory.getAllProducts().get(i).getId() == newID) {
                newID++;
            }
        }
        return newID;
    }


    /**
     * updates the associated parts table
     */
    @FXML
    private void updateAssociatedPartTableView() {
        AssociatedPartsTableView.setItems(associatedParts);
    }


    /**
     * updates the parts table
     */
    public void updatePartTableView() {
        PartsTableView.setItems(Inventory.getAllParts());
    }


    /**
     * adds a part to the associated parts table
     * also removes the part from the  main parts table
     */
    @FXML
    void AddProductAddButtonClick(ActionEvent event) {
        Part selectedPart = PartsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedParts.add(selectedPart);
            originalParts.remove(selectedPart);
            updateAssociatedPartTableView();
        }


    }

    /**
     * cancel button click
     * goes back to the main view
     */
    @FXML
    void AddProductCancelButtonClick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneMain(event);

    }

    /**
     * remove associated part button click
     * removes the part from the associated parts table
     */

    @FXML
    void AddProductRemoveAssociatedPartButtonClick(ActionEvent event) {
        Part selectedPart = AssociatedPartsTableView.getSelectionModel().getSelectedItem();
        associatedParts.remove(selectedPart);
        originalParts.add(selectedPart);
        updateAssociatedPartTableView();


    }


    /**
     *save button click
     * saves the product
     */
    @FXML
    void AddProductSaveButtonClick(ActionEvent event) throws Exception {
        int id = Integer.parseInt(AddProductIdTextPrompt.getText());
        String name = AddProductNameTextPrompt.getText();
        int stock = Integer.parseInt(AddProductInvTextPrompt.getText());
        double price = Double.parseDouble(AddProductPriceTextPrompt.getText());
        int min = Integer.parseInt(AddProductMinTextPrompt.getText());
        int max = Integer.parseInt(AddProductMaxTextPrompt.getText());
        ObservableList<Part> associatedParts = AssociatedPartsTableView.getItems();
        //logic checks
        if (name.isEmpty()) {
            Alerts.alert("Name field empty", "Name field empty", "Name field empty");
            return;
        } else if (min > max) {
            Alerts.alert("Min is over max", "Min is over max", "Min is over max");
            return;

        } else if (stock < min || stock > max) {
            Alerts.alert("Inventory is not within min and max", "Inventory is not within min and max", "Inventory is not within min and max");
            return;
        } else {

            //get all associated parts in parts array
            Part[] parts = new Part[associatedParts.size()];
            for (int i = 0; i < associatedParts.size(); i++) {
                parts[i] = associatedParts.get(i);
            }


            Product product = new Product(Inventory.getProductIDCount(), name, price, stock, min, max);

            //add the parts to the product
            for (int i = 0; i < parts.length; i++) {
                product.addAssociatedPart(parts[i]);
            }

            Inventory.addProduct(product);

            //put the parts back in the original parts list
            for (int i = 0; i < parts.length; i++) {
                originalParts.add(parts[i]);
            }
            //order the parts list by id number and update the table view
            for (int i = 0; i < originalParts.size(); i++) {
                for (int j = 0; j < originalParts.size(); j++) {
                    if (originalParts.get(i).getId() < originalParts.get(j).getId()) {
                        Part temp = originalParts.get(i);
                        originalParts.set(i, originalParts.get(j));
                        originalParts.set(j, temp);
                    }
                }
            }
            SceneChanger sc = new SceneChanger();
            sc.switchToSceneMain(event);

        }
    }


    /**
     * search for a part
     */
    public void search (KeyEvent keyEvent){
            String search = AddProductSearchTextPrompt.getText();
            ObservableList<Part> searchResults = FXCollections.observableArrayList();
        //search function that searches for the part name
            for (int i = 0; i < originalParts.size(); i++) {
                if (originalParts.get(i).getName().contains(search)) {
                    searchResults.add(originalParts.get(i));
                }

            }
            PartsTableView.setItems(searchResults);
        }
    }
