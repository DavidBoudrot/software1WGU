module david.software1.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens david.software1.demo1 to javafx.fxml;
    exports david.software1.demo1;
}