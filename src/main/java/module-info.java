module david.software1.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    opens david.software1.demo1.models to javafx.base;


    opens david.software1.demo1 to javafx.fxml;
    exports david.software1.demo1;
    exports david.software1.demo1.controllers;
    opens david.software1.demo1.controllers to javafx.fxml;
}