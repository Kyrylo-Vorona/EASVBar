package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateEventController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtStartDate;
    @FXML
    private TextField txtEndDate;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtLocationGuidance;
    @FXML
    private TextField txtTickets;
    @FXML
    private TextArea txtNotes;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    @FXML
    private void handleCreateEvent(ActionEvent event) {
        try {
            logic.createEvent(txtName.getText(), txtStartDate.getText(), txtEndDate.getText(), txtLocation.getText(), txtLocationGuidance.getText(), txtNotes.getText(), Integer.parseInt(txtTickets.getText()));
            handleCancel(event);
        } catch (Exception e) {
            OpenView.showErrorAlert("Error creating event: " + e.getMessage());
        }
    }

    @FXML
     private void handleCancel(ActionEvent event) {
         try {
             String filepath = "/dk/easv/easvbar/gui/CoordinatorEventManagementView.fxml";
             openview.openView(filepath, event);
         } catch (EventException e) {
             OpenView.showErrorAlert(e.getMessage());
         }
    }
}
