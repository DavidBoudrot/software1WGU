package david.software1.demo1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPartinHouseController {

    @FXML
    private Button AddPartCancelButton;

    @FXML
    private TextField AddPartIDTextPrompt;

    @FXML
    private RadioButton AddPartInHouseButton;

    @FXML
    private TextField AddPartInvTextPrompt;

    @FXML
    private TextField AddPartMachineIDTextPrompt;

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

    @FXML
    void AddPartCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void AddPartInHouseButtonClick(ActionEvent event) throws Exception {
        SceneChanger sc = new SceneChanger();
        sc.switchToSceneAddPartInHouse(event);

    }

    @FXML
    void AddPartOutsourcedButtonClick(ActionEvent event) {

    }

    @FXML
    void AddPartSaveButtonClick(ActionEvent event) {

    }

}
