package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import dk.easv.easvbar.dal.DALManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminEventManagementController implements Initializable {
    @FXML
    private TableColumn<Event, String> eventNameColumn;
    @FXML
    private TableColumn<Event, String> eventDateColumn;
    @FXML
    private TableColumn<Event, String> eventLocationColumn;
    @FXML
    private TableColumn<Event, String> coordinatorsColumn;
    @FXML
    private TableView<Event> eventTable;
    private ObservableList<Event> eventList;

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

    public void backToUserManagement(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AdminUserManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void openAssignCoordinatorView(ActionEvent event) {
        Event selected = eventTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = openview.openView("/dk/easv/easvbar/gui/assignCoordinatorView.fxml", event);
                AssignCoordinatorController controller = loader.getController();
                controller.setLabel(selected);
            } catch (EventException e) {
                OpenView.showErrorAlert(e.getMessage());
            }
        } else {
            OpenView.showErrorAlert("Please select an event to go");
        }
    }

    public void deleteEvent (ActionEvent event) {
        Event selected = eventTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                logic.deleteEvent(selected);
                readDataIntoList();
            } catch (EventException e) {
                OpenView.showErrorAlert(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readDataIntoList();
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    private void readDataIntoList() throws EventException {
        eventList = FXCollections.observableArrayList();
        List<Event> allEvents = logic.getAllEvents();
        for (Event e : allEvents) {
            try {
                String names = logic.getCoordinatorsForEvent(e.getId());
                e.setCoordinatorNames(names);
                eventList.add(e);
            } catch (Exception ex) {
                e.setCoordinatorNames("Error loading");
                eventList.add(e);
            }
        }
        eventTable.setItems(eventList);
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        coordinatorsColumn.setCellValueFactory(new PropertyValueFactory<>("coordinatorNames"));
    }
}
