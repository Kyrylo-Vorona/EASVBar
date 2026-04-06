package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import javafx.event.ActionEvent;

public class AdminUserManagementController {

    OpenView openview = new OpenView();

    public void logOut(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/MainView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void backToEventManagement(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AdminEventManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void openAddUserView(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AddUserView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
