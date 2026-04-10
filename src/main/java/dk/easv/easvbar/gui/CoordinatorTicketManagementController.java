package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;

public class CoordinatorTicketManagementController {

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void backToEventManagement(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/CoordinatorEventManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
