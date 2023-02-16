package david.software1.demo1.controllers;

import david.software1.demo1.models.InHousePart;
import david.software1.demo1.models.Inventory;
import david.software1.demo1.models.OutsourcedPart;
import david.software1.demo1.models.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * The ModifyPartOutsourcedController class controls the Modify Part Outsourced view.
 * it swaps the company name and machine ID fields.
 */
public class ModifyPartOutsourcedController {

    @FXML
    private RadioButton AddPartOutsourcedButton;

    @FXML
    private ToggleGroup InHouseOrOutsourced;

    @FXML
    private TextField ModifyCompanyTextField;

    @FXML
    private TextField ModifyMaxTextField;

    @FXML
    private TextField ModifyMinTextField;

    @FXML
    private Button ModifyPartCancelButton;

    @FXML
    private TextField ModifyPartIDTextField;

    @FXML
    private RadioButton ModifyPartInHouseButton;

    @FXML
    private TextField ModifyPartInvTextField;

    @FXML
    private TextField ModifyPartNameTextField;

    @FXML
    private Button ModifyPartSaveButton;

    @FXML
    private TextField ModifyPriceTextField;


    /**
     * The initialize method sets the part ID field to the next available part ID.
     * it also disables the part ID field so that the user cannot change it.
     */
    @FXML
    public void initialize() {
        ModifyPartIDTextField.setText(Integer.toString(Inventory.getPartIDCount()));
        ModifyPartIDTextField.setDisable(true);

    }


    /**
     * the ModifyPartCancelButtonClick method switches the scene to the main view.
     */
    @FXML
    void ModifyPartCancelButtonClick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneMain(event);

    }


    /**
     * the ModifyPartInHouseButtonclick method switches the scene to the Modify Part In-House view.
     */
    @FXML
    void ModifyPartInHouseButtonclick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneModifyPartInHouse(event);


    }


    /**
     * the ModifyPartOutsourcedButtonClick method does nothing since the user is already on the Modify Part Outsourced view.
     */
    @FXML
    void ModifyPartOutsourcedButtonClick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneModifyPartOutsourced(event);

    }


    /**
     * the setPartInHouse method sets the text fields to the values of the part passed in.
     */
    @FXML
    public void setPartInHouse (OutsourcedPart part) {
        ModifyPartIDTextField.setText(Integer.toString(part.getId()));
        ModifyPartNameTextField.setText(part.getName());
        ModifyPriceTextField.setText(Double.toString(part.getPrice()));
        ModifyPartInvTextField.setText(Integer.toString(part.getStock()));
        ModifyMinTextField.setText(Integer.toString(part.getMin()));
        ModifyMaxTextField.setText(Integer.toString(part.getMax()));
        ModifyCompanyTextField.setText(part.getCompanyName());
    }


    /**
     * the ModifyPartSaveButtonClick method saves the part to the inventory.
     * it also switches the scene to the main view.
     */
    @FXML
    void ModifyPartSaveButtonClick(ActionEvent event) throws Exception {

        int id = Integer.parseInt(ModifyPartIDTextField.getText());
        String name = ModifyPartNameTextField.getText();
        double price = Double.parseDouble(ModifyPriceTextField.getText());
        int stock = Integer.parseInt(ModifyPartInvTextField.getText());
        int min = Integer.parseInt(ModifyMinTextField.getText());
        int max = Integer.parseInt(ModifyMaxTextField.getText());
        int machineID = Integer.parseInt(ModifyCompanyTextField.getText());

        InHousePart part = new InHousePart(id, name, price, stock, min, max, machineID);
        Inventory.updatePart(id - 1, part);
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneMain(event);


    }



    /**
     * the sendPartToModifyPartOutsourced method sets the text fields to the values of the part passed in.
     */
    public void sendPartToModifyPartOutsourced(TableView<Part> partTable) {
        Part part = partTable.getSelectionModel().getSelectedItem();
        if (part instanceof OutsourcedPart) {
            setPartInHouse((OutsourcedPart) part);
        }
    }

}
