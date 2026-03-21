module dk.easv.easvbar {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.easvbar to javafx.fxml;
    exports dk.easv.easvbar;
    exports dk.easv.easvbar.gui;
    opens dk.easv.easvbar.gui to javafx.fxml;
}