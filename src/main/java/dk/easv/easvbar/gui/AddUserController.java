package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    OpenView openview = new OpenView();

    public void cancel(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AdminUserManagementView.fxml";
            openview.openView(filepath, event);
        }catch(EventException e){
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void addUser(ActionEvent event) {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String role = roleField.getText();
            logic.addUser(username, password, email, role);
            String filepath = "/dk/easv/easvbar/gui/AdminUserManagementView.fxml";
            openview.openView(filepath, event);
        }catch (EventException e){
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
