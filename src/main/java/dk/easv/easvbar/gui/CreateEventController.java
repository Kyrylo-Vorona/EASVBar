package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField txtPrice;
    @FXML
    private TextArea txtNotes;
    @FXML
    private Button btnSave;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    private Event eventToEdit;

    @FXML
    private void handleCreateEvent(ActionEvent event) {
        try {
            String name = txtName.getText();
            String startDate = txtStartDate.getText();
            String endDate = txtEndDate.getText();
            String location = txtLocation.getText();
            String locationGuidance = txtLocationGuidance.getText();
            int price = Integer.parseInt(txtPrice.getText());
            String notes = txtNotes.getText();
            if (eventToEdit == null) {
                logic.createEvent(name, startDate, endDate, location, locationGuidance, notes, price);
            } else {
                eventToEdit.setName(name);
                eventToEdit.setStartTime(startDate);
                eventToEdit.setEndTime(endDate);
                eventToEdit.setLocation(location);
                eventToEdit.setLocationGuidance(locationGuidance);
                eventToEdit.setNotes(notes);
                eventToEdit.setPrice(price);
                logic.editEvent(eventToEdit);
            }
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

    public void setEventData(Event event) {
        this.eventToEdit = event;
        txtName.setText(event.getName());
        txtStartDate.setText(event.getStartTime());
        txtEndDate.setText(event.getEndTime());
        txtLocation.setText(event.getLocation());
        txtLocationGuidance.setText(event.getLocationGuidance());
        txtPrice.setText(String.valueOf(event.getPrice()));
        btnSave.setText("Save");
    }
}
