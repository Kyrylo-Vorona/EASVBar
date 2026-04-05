package dk.easv.easvbar.gui;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminUserManagementController {

    OpenView openview = new OpenView();

    public void logOut(ActionEvent event) throws IOException {
        String filepath = "/dk/easv/easvbar/gui/MainView.fxml";
        openview.openView(filepath, event);
    }

    public void backToEventManagement(ActionEvent event) throws IOException {
        String filepath = "/dk/easv/easvbar/gui/AdminEventManagementView.fxml";
        openview.openView(filepath, event);
    }

    public void openAddUserView(ActionEvent event) throws IOException {
        String filepath = "/dk/easv/easvbar/gui/AddUserView.fxml";
        openview.openView(filepath, event);
    }
}
