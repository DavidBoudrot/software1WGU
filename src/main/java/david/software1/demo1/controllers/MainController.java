package david.software1.demo1.controllers;

import david.software1.demo1.models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * The MainController class controls the Main view.
 * it presents the user with a list of parts and products.
 * the user can add, modify, and delete parts and products.
 * the user can also search for parts and products.
 */

public class MainController {

    @FXML
    private Button addPartButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private TextField partSearchTextField;

    @FXML
    private TableView<Part> partTable;


    @FXML
    private TextField productSearchTextField;

    @FXML
    private TableView<Product> productTable;
    
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;




    // populate part table



    /**
     * The initialize method populates the part and product tables.
     */
    @FXML
    public void initialize() {
        //populate part table
        ObservableList<Part> parts = Inventory.getAllParts();
        System.out.println("Parts: " + parts);
        partTable.setItems(parts);
        TableColumn<Part, Integer> PartNumber = new TableColumn<>("Part Number");
        PartNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> PartName = new TableColumn<>("Part Name");
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, Integer> PartInv = new TableColumn<>("Inventory Level");
        PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, Double> PartPrice = new TableColumn<>("Price");
        PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartPrice.setPrefWidth(125);
        partTable.getColumns().setAll(PartNumber, PartName, PartInv, PartPrice);
        System.out.println("Part table populated");

        //populate product table
        ObservableList<Product> products = Inventory.getAllProducts();
        productTable.setItems(products);
        TableColumn<Product, Integer> ProductNumber = new TableColumn<>("Product Number");
        ProductNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> ProductName = new TableColumn<>("Product Name");
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> ProductInv = new TableColumn<>("Inventory Level");
        ProductInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Product, Double> ProductPrice = new TableColumn<>("Price");
        ProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductPrice.setPrefWidth(125);
        productTable.getColumns().setAll(ProductNumber, ProductName, ProductInv, ProductPrice);
        System.out.println("Product table populated");

    }


    /**
     * The addPartButtonClick method changes the scene to the AddPartInHouse view.
     */
    @FXML
    void addPartButtonClick(ActionEvent event) throws Exception {
        //change scene to add part in house view
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneAddPartInHouse(event);

    }


    /**
     * The addProductButtonClick method changes the scene to the AddProduct view.
     */
    @FXML
    void addProductButtonClick(ActionEvent event) throws Exception {
        //change scene to add product view
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneAddProduct(event);


        }


    /**
     * The modifyPartButtonClick method changes the scene to the ModifyPartInHouse view or the ModifyPartOutsourced view.
     */
    @FXML
    void modifyPartButtonClick(ActionEvent event) throws Exception {
        //change scene to modify part in house view
        if (partTable.getSelectionModel().getSelectedItem() == null) {
            System.out.println("No part selected");
            return;
        } else if (partTable.getSelectionModel().getSelectedItem() instanceof OutsourcedPart) {
            SceneChanger sc = new SceneChanger();
            sc.switchToSceneModifyPartOutsourced(this, partTable, event);

        } else {
            SceneChanger sc = new SceneChanger();
            sc.switchToSceneModifyPartInHouse(this, partTable, event);
        }


    }

    /**
     * The modifyProductButtonClick method changes the scene to the ModifyProduct view.
     */
    @FXML
    void modifyProductButtonClick(ActionEvent event) throws Exception {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alerts.alert("No product selected", "No product selected", "No product selected");
            return;
        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/david/software1/demo1/views/ModifyProductView.fxml"));
            root = loader.load();

            ModifyProductController controller = loader.getController();
            controller.setProduct(selectedProduct);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }




    /**
     * The deletePartButtonClick method deletes the selected part.
     */
    @FXML
    void deletePartButtonClick(ActionEvent event) {
        //check if part is in use
        Part part = partTable.getSelectionModel().getSelectedItem();
        if (Inventory.isPartInUse(part)) {
            //alert user that part is in use
            Alerts.alert("Part is in use", "Part is in use", "Part is in use");
            return;
        } else {
            //delete part
            Inventory.deletePart(part);
        }

    }


    /**
     * The deleteProductButtonClick method deletes the selected product.
     */
    @FXML
    void deleteProductButtonClick(ActionEvent event) {
        //get selected product
        Product product = productTable.getSelectionModel().getSelectedItem();
        //delete product
        Inventory.deleteProduct(product);

    }

    /**
     * The exitButtonClick method exits the program.
     */
    @FXML
    void exitButtonClick(ActionEvent event) {
        System.exit(0);

    }



    /**
     * The partSearchTextField method searches the part table.
     */
    public void partSearchTextField(KeyEvent keyEvent) {

        String search = partSearchTextField.getText();
        ObservableList<Part> parts = Inventory.lookupPart(search);
        partTable.setItems(parts);
        if (parts.size() == 0) {
            try {
                //try to parse the search string as an int
                int partID = Integer.parseInt(search);
                Part part = Inventory.lookupPart(partID);
                if (part != null) { //if part is found, add it to the list
                    parts.add(part);
                }
            } catch (NumberFormatException e) {
                //ignore
            }
        }
    }


    /**
     * The productSearchTextField method searches the product table.
     */
    public void productSearchTextField(KeyEvent keyEvent) {
        //search product table
        String searchProduct = productSearchTextField.getText();
        ObservableList<Product> products = Inventory.lookupProduct(searchProduct);
        productTable.setItems(products);
       //if no products are found, try to parse the search string as an int
        if (products.size() == 0) {
            try {
                int productID = Integer.parseInt(searchProduct);
                Product product = Inventory.lookupProduct(productID);
                if (product != null) {
                    products.add(product);
                }
            } catch (NumberFormatException e) {
            }
        }
    }

}
