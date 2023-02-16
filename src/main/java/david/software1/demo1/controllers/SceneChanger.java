package david.software1.demo1.controllers;

import david.software1.demo1.models.InHousePart;
import david.software1.demo1.models.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The SceneChanger class is used to change the scene from one view to another.
 * I created this class to make it easier to change scenes.
 * It ended up becoming a headache to use because I had to pass data between scenes and it got hard to keep track of.
 * I stopped using this class once I had to modify products.
 */

public class SceneChanger {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneAddPartInHouse(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/david/software1/demo1/views/AddPartInHouseView.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToSceneMain(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/david/software1/demo1/views/MainView.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToSceneAddPartOutsourced(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/david/software1/demo1/views/AddPartOutsourcedView.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSceneAddProduct(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/david/software1/demo1/views/AddProduct.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //
    }

    public void switchToSceneModifyPartInHouse(MainController mainController, TableView<Part> partTable, ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/david/software1/demo1/views/ModifyPartInHouse.fxml"));
        root = loader.load();
        ModifyPartInHouseController controller = loader.getController();
        controller.sendPartToModifyPartInHouse(mainController, partTable);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void switchToSceneModifyPartOutsourced(MainController mainController, TableView<Part> partTable, ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/david/software1/demo1/views/ModifyPartOutsourced.fxml"));
        root = loader.load();
        ModifyPartOutsourcedController controller = loader.getController();
        controller.sendPartToModifyPartOutsourced(partTable);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneModifyPartOutsourced(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/david/software1/demo1/views/ModifyPartOutsourced.fxml"));
        root = loader.load();
        ModifyPartOutsourcedController controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneModifyPartInHouse(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/david/software1/demo1/views/ModifyPartInHouse.fxml"));
        root = loader.load();
        ModifyPartInHouseController controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}


