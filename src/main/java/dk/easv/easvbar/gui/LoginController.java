package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Logic logic = Logic.getInstance();

    public void onLoginClick(ActionEvent event) throws SQLException {
        try {
            User loggedInUser = logic.login(username.getText(), password.getText());
            if (loggedInUser != null) {

                if (loggedInUser.getRole().equalsIgnoreCase("ADMIN")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/AdminUserManagementView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 700, 450);
                    if (getClass().getResource("/css/style.css") != null) {
                        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
                    }
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/CoordinatorEventManagementView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 700, 450);
                    if (getClass().getResource("/css/style.css") != null) {
                        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
                    }
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        if (getClass().getResource("/css/style.css") != null) {
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
