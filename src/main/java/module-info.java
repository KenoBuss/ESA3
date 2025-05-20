module esa.esa3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens esa.esa3 to javafx.fxml;
    exports esa.esa3;
}