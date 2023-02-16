package david.software1.demo1;

import david.software1.demo1.models.InHousePart;
import david.software1.demo1.models.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static david.software1.demo1.models.Inventory.addPart;





/**
 * This class represents an inventory manager application. It allows the user to manage parts and products
 * in an inventory system. This class extends the JavaFX Application class and uses an FXML file for the user interface.
 */
public class InventoryManager extends Application {

    /**
     * The start method initializes the user interface and sets up the primary stage.
     *
     * @param stage the primary stage for the application
     * @throws IOException if there is an error loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryManager.class.getResource("/david/software1/demo1/views/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("David Boudrot Software 1 Project");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method is the entry point for the application. It creates some sample InHousePart objects and adds them
     * to the inventory, then launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InHousePart part = new InHousePart(0, "Part 1", 1.00, 1, 1, 1, 1);
        addPart(part);
        InHousePart part2 = new InHousePart(1, "Part 2", 2.00, 2, 2, 2, 2);
        addPart(part2);
        InHousePart part3 = new InHousePart(2, "Part 3", 3.00, 3, 3, 3, 3);
        addPart(part3);
        InHousePart part4 = new InHousePart(3, "Part 4", 4.00, 4, 4, 4, 4);
        addPart(part4);
        InHousePart part5 = new InHousePart(4, "Part 5", 5.00, 5, 5, 5, 5);
        addPart(part5);
        InHousePart part6 = new InHousePart(5, "Part 6", 6.00, 6, 6, 6, 6);
        addPart(part6);

        launch();
    }

}
