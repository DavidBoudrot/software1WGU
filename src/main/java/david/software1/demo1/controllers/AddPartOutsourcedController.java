package david.software1.demo1.controllers;

import david.software1.demo1.models.InHousePart;
import david.software1.demo1.models.Inventory;
import david.software1.demo1.models.OutsourcedPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPartOutsourcedController {


    /**
     * The initialize method sets the part ID field to the next available part ID.
     * it also disables the part ID field so that the user cannot change it.
     */
    @FXML
    public void initialize() {
        AddPartIDTextPrompt.setText(Integer.toString(Inventory.getPartIDCount()));
        AddPartIDTextPrompt.setDisable(true);
    }
    @FXML
    private Button AddPartCancelButton;

    @FXML
    private TextField AddPartIDTextPrompt;

    @FXML
    private RadioButton AddPartInHouseButton;

    @FXML
    private TextField AddPartInvTextPrompt;

    @FXML
    private TextField AddPartCompanyTextPrompt;

    @FXML
    private TextField AddPartMaxTextPrompt;

    @FXML
    private TextField AddPartMinTextPrompt;

    @FXML
    private TextField AddPartNameTextPrompt;

    @FXML
    private RadioButton AddPartOutsourcedButton;

    @FXML
    private TextField AddPartPriceTextPrompt;

    @FXML
    private Button AddPartSaveButton;

    @FXML
    private ToggleGroup InHouseOrOutsourced;


    /**
     * The AddPartCancelButtonClick method switches the scene to the main scene.
     */
    @FXML
    void AddPartCancelButtonClick(ActionEvent event) throws Exception {

        SceneChanger sc = new SceneChanger();
        sc.switchToSceneMain(event);

    }


    /**
     * The AddPartInHouseButtonClick method switches the scene to the add part in house scene.
     */
    @FXML
    void AddPartInHouseButtonClick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneAddPartInHouse(event);

    }


    /**
     * The AddPartOutsourcedButtonClick method switches the scene to the add part outsourced scene.
     * it does not do anything since the user is already on the add part outsourced scene.
     */
    @FXML
    void AddPartOutsourcedButtonClick(ActionEvent event) throws Exception {


    }


    /**
     * The AddPartSaveButtonClick method creates a new part object and adds it to the inventory.
     * it then switches the scene to the main scene.
     */
    @FXML
    void AddPartSaveButtonClick(ActionEvent event) throws Exception {
            int id = Integer.parseInt(AddPartIDTextPrompt.getText());
            String name = AddPartNameTextPrompt.getText();
            double price = Double.parseDouble(AddPartPriceTextPrompt.getText());
            int stock = Integer.parseInt(AddPartInvTextPrompt.getText());
            int min = Integer.parseInt(AddPartMinTextPrompt.getText());
            int max = Integer.parseInt(AddPartMaxTextPrompt.getText());
            String companyName = AddPartCompanyTextPrompt.getText();
            OutsourcedPart part = new OutsourcedPart(id, name, price, stock, min, max, companyName);
            Inventory.addPart(part);
            //makes a new part object and adds it to the inventory
            SceneChanger sc = new SceneChanger();
            sc.switchToSceneMain(event);

        }

}
