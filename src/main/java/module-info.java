module dk.easv.easvbar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;


    opens dk.easv.easvbar to javafx.fxml;
    exports dk.easv.easvbar;
    exports dk.easv.easvbar.gui;
    opens dk.easv.easvbar.gui to javafx.fxml;
}