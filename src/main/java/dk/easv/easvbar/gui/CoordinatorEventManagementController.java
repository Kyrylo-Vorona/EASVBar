package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;

public class CoordinatorEventManagementController {

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void logOut(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/MainView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void backToTicketManagement(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/CoordinatorTicketManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void openSoldTicketsView(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/SoldTicketsView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void CreateEvent(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/CreateEventView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
