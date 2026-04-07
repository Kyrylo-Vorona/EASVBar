package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button saveButton;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();
    private User userToEdit;

    public void cancel(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AdminUserManagementView.fxml";
            openview.openView(filepath, event);
        }catch(EventException e){
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void setUserData(User user) {
        this.userToEdit = user;
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        roleField.setText(user.getRole());
        saveButton.setText("Update");
    }

    public void addUser(ActionEvent event) {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String role = roleField.getText();
            if(userToEdit == null) {
                logic.addUser(username, password, email, role);
            } else {
                userToEdit.setUsername(username);
                userToEdit.setEmail(email);
                userToEdit.setRole(role);
                logic.editUser(userToEdit);
            }
            cancel(event);
        }catch (EventException e){
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
