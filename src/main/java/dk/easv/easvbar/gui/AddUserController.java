package dk.easv.easvbar.gui;

import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddUserController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField roleField;

    private Logic logic = Logic.getInstance();

    public void cancel(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/AdminUserManagementView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        if (getClass().getResource("/css/style.css") != null) {
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void addUser(ActionEvent actionEvent) throws SQLException {
        String username = usernameField.getText();
        String password =  passwordField.getText();
        String email = emailField.getText();
        String role = roleField.getText();
        logic.addUser(username, password, email, role);
    }
}
