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

public class ModifyProductController {

    @FXML
    private Button ModifyProductAddButton;

    @FXML private TableView<Part> PartsTableView;
    @FXML private TableColumn<Part, Integer> PartsIDColumn;

    @FXML private TableColumn<Part, String> PartsNameColumn;
    @FXML private TableColumn<Part, Integer> PartsInventoryColumn;
    @FXML private TableColumn<Part, Double> PartsCostColumn;



    //Associated Parts Table
    @FXML private TableView<Part> AssociatedPartsTableView;
    @FXML private TableColumn<Part, Integer> AssociatedPartsIDColumn;
    @FXML private TableColumn<Part, String> AssociatedPartsNameColumn;
    @FXML private TableColumn<Part, Integer> AssociatedPartsInventoryColumn;
    @FXML private TableColumn<Part, Double> AssociatedPartsCostColumn;

    @FXML
    private Button ModifyProductCancelButton;

    @FXML
    private TextField ModifyProductIdTextPrompt;

    @FXML
    private TextField ModifyProductInvTextPrompt;

    @FXML
    private TextField ModifyProductMaxTextPrompt;

    @FXML
    private TextField ModifyProductMinTextPrompt;

    @FXML
    private TextField ModifyProductNameTextPrompt;

    @FXML
    private TextField ModifyProductPriceTextPrompt;

    @FXML
    private Button ModifyProductRemoveAssociatedPartButton;

    @FXML
    private Button ModifyProductSaveButton;

    @FXML
    private TextField ModifyProductSearchTextPrompt;

    @FXML
    private Product selectedProduct;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private Product modProduct;
    private int productID;


    /**
     * The initialize method just makes the id text prompt uneditable.
     */
    @FXML
    void initialize() {
        ModifyProductIdTextPrompt.setEditable(false);
        //this method makes the id text prompt uneditable
        }


        /**
         * The search method searches for parts in the inventory.
         */
        @FXML
        void search(KeyEvent event) {
            String search = ModifyProductSearchTextPrompt.getText();
            ObservableList<Part> searchResults = Inventory.lookupPart(search);

            // if search results is empty, try to search by id
            if(searchResults.size() == 0) {
                try {
                    int id = Integer.parseInt(search);
                    Part part = Inventory.lookupPart(id);
                    if(part != null) {
                        // if we find a part, add it to the search results
                        searchResults.add(part);
                    }
                } catch (NumberFormatException e) {
                    // if the search is not a number, do nothing
                }
            }
            PartsTableView.setItems(searchResults);
        }



        /**
         * The ModifyProductAddButtonClick method adds a part to the associated parts table.
         */
    @FXML
    void ModifyProductAddButtonClick(ActionEvent event) {
        //Add Associated Part
        Part selectedPart = PartsTableView.getSelectionModel().getSelectedItem();
        AssociatedPartsTableView.getItems().add(selectedPart);
        PartsTableView.getItems().remove(selectedPart);



    }

    /**
     * The ModifyProductCancelButtonClick method cancels the modification of a product.
     */
    @FXML
    void ModifyProductCancelButtonClick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneMain(event);
    }


    /**
     * The ModifyProductRemoveAssociatedPartButtonClick method removes an associated part from the associated parts table.
     */

    @FXML
    void ModifyProductRemoveAssociatedPartButtonClick(ActionEvent event) {
        Part selectedPart = AssociatedPartsTableView.getSelectionModel().getSelectedItem();
        PartsTableView.getItems().add(selectedPart);
        AssociatedPartsTableView.getItems().remove(selectedPart);




    }


    /**
     * The ModifyProductSaveButtonClick method saves the modifications to a product.
     */
    @FXML
    void ModifyProductSaveButtonClick(ActionEvent event) throws Exception {
        int productInventory = Integer.parseInt(ModifyProductInvTextPrompt.getText());
            int productMinimum = Integer.parseInt(ModifyProductMinTextPrompt.getText());
            int productMaximum = Integer.parseInt(ModifyProductMaxTextPrompt.getText());

                //Here are just some logic checks to make sure that the data entered makes sense.
                if(productMaximum < productMinimum) {
                    //error message
                    Alerts.alert("Input Error", "Error", "Maximum must be greater than Minimum");
                }
                else if(productInventory < productMinimum || productInventory > productMaximum) {
                    Alerts.alert("Input Error", "Error", "Inventory must be between Minimum and Maximum" );
                }
                else {
                    this.modProduct = selectedProduct;
                    selectedProduct.setId(Integer.parseInt(ModifyProductIdTextPrompt.getText()));
                    selectedProduct.setName(ModifyProductNameTextPrompt.getText());
                    selectedProduct.setStock(Integer.parseInt(ModifyProductInvTextPrompt.getText()));
                    selectedProduct.setPrice(Double.parseDouble(ModifyProductPriceTextPrompt.getText()));
                    selectedProduct.setMax(Integer.parseInt(ModifyProductMaxTextPrompt.getText()));
                    selectedProduct.setMin(Integer.parseInt(ModifyProductMinTextPrompt.getText()));
                    selectedProduct.getAssociatedParts().clear(); //gets rid of old associated parts
                    associatedParts = AssociatedPartsTableView.getItems(); //gets new associated parts
                    selectedProduct.setAssociatedParts(associatedParts); //sets new associated parts
                    //Back to Main Screen
                    SceneChanger sc = new SceneChanger();
                    sc.switchToSceneMain(event);
                }
                //add associated parts back to all parts list
                for (Part part : selectedProduct.getAssociatedParts()) {
                    PartsTableView.getItems().add(part);
                }




    }


    /**
     * The setProduct method sets the product to be modified.
     * we need this method to be called because we are passing the product from the main screen to this screen.
     */
    public void setProduct(Product selectedProduct) {

        this.selectedProduct = selectedProduct;
        this.productID = selectedProduct.getId();
        ModifyProductIdTextPrompt.setText(Integer.toString(selectedProduct.getId()));
        ModifyProductNameTextPrompt.setText(selectedProduct.getName());
        ModifyProductInvTextPrompt.setText(Integer.toString(selectedProduct.getStock()));
        ModifyProductPriceTextPrompt.setText(Double.toString(selectedProduct.getPrice()));
        ModifyProductMaxTextPrompt.setText(Integer.toString(selectedProduct.getMax()));
        ModifyProductMinTextPrompt.setText(Integer.toString(selectedProduct.getMin()));

        //populate columns with data for all parts
        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartsTableView.setItems(Inventory.getAllParts());

        //populate columns with data for associated parts
        AssociatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssociatedPartsTableView.setItems(selectedProduct.getAssociatedParts());


        //remove associated parts from all parts list
        for (Part part : selectedProduct.getAssociatedParts()) {
            PartsTableView.getItems().remove(part);
        }







    }
}






