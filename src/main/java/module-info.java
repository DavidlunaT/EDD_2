module EDD_2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens EDD_2 to javafx.fxml;
    exports EDD_2;
    opens EDD_2.controllers to javafx.fxml;
    exports EDD_2.controllers;
}
