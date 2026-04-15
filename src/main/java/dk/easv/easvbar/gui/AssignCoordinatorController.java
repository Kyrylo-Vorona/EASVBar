package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignCoordinatorController implements Initializable {
    @FXML
    private Label selectedEvent;
    @FXML
    private TableView<User> tblCoordinators;
    @FXML
    private TableColumn<User, String> colName, colEmail;

    private String nameOfEvent;
    private Event currentEvent;
    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();
    private String previousViewPath;

    public void setPreviousView(String path) {
        this.previousViewPath = path;
    }

    public void setLabel(Event selected) {
        this.currentEvent = selected;
        this.nameOfEvent = "Event: " + selected.getName();
        selectedEvent.setText(nameOfEvent);
        loadCoordinators();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadCoordinators() {
        try {
            tblCoordinators.getItems().setAll(logic.getAllCoordinators());
        } catch (Exception e) {
            OpenView.showErrorAlert("Could not load coordinators.");
        }
    }

    @FXML
    private void handleAssign(ActionEvent event) {
        User selectedUser = tblCoordinators.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                logic.assignCoordinatorToEvent(selectedUser.getId(), currentEvent.getId());
                cancel(event);
            } catch (Exception e) {
                OpenView.showErrorAlert(e.getMessage());
            }
        }
    }

    public void cancel(ActionEvent event) {
        try {
            String filepath = previousViewPath;
            openview.openView(filepath, event);
        }catch(EventException e){
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
