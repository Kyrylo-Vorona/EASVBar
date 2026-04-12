package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.Ticket;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateTicketController {
    @FXML
    private Label lblEventName;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnCreate;

    private Ticket ticketToEdit;
    private Event ourEvent;
    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void setTicketData(Ticket selected, Event currentEvent) {
        ourEvent = currentEvent;
        ticketToEdit = selected;
        lblEventName.setText("Edit ticket: " + selected.getName());
        txtType.setText(selected.getName());
        txtDescription.setText(selected.getDescription());
        txtPrice.setText(selected.getPrice() + "");
        btnCreate.setText("Edit");
    }

    public void setLabel(Event currentEvent) {
        ourEvent = currentEvent;
        lblEventName.setText("Create ticket for: " + currentEvent.getName());
    }

    public void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = openview.openView("/dk/easv/easvbar/gui/CoordinatorTicketManagementView.fxml", event);
            CoordinatorTicketManagementController controller = loader.getController();
            controller.setLabel(ourEvent);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
