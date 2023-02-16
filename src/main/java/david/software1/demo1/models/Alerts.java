package david.software1.demo1.models;
//alerts for javaFX program

import javafx.scene.control.Alert;
/**
 * The Alerts class is a utility class that contains static methods for displaying alerts.
 *
 */
public class Alerts {

    /**
     *
     * @param title
     * @param header
     * @param content
     */
    public static void alert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}



