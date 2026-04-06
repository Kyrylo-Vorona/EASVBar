package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import javafx.event.ActionEvent;

public class BuyTicketController {
    OpenView openview = new OpenView();

    public void onBuyTicketClick(ActionEvent actionEvent) {
    }

    public void onCancelClick(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/MainView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
