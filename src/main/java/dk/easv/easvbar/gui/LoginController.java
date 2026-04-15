package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    // to log in as an admin: username "admin" ; password "admin123"
    // to log in as a coordinator: username "Coordinator1" ; password "coordinator123"

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void onLoginClick(ActionEvent event) {
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
            } else {
                OpenView.showErrorAlert("Wrong username or password");
            }
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void onBackClick(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/MainView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
