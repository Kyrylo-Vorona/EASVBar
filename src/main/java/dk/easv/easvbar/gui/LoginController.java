package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void onLoginClick(ActionEvent event) throws SQLException {
        try {
            User loggedInUser = logic.login(username.getText(), password.getText());
            if (loggedInUser != null) {

                if (loggedInUser.getRole().equalsIgnoreCase("ADMIN")) {
                    String filepath = "/dk/easv/easvbar/gui/AdminUserManagementView.fxml";
                    openview.openView(filepath, event);
                } else {
                    String filepath = "/dk/easv/easvbar/gui/CoordinatorEventManagementView.fxml";
                    openview.openView(filepath, event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackClick(ActionEvent event) throws IOException {
        String filepath = "/dk/easv/easvbar/gui/MainView.fxml";
        openview.openView(filepath, event);
    }
}
