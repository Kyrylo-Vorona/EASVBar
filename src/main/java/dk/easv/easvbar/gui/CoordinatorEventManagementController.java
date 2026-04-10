package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorEventManagementController implements Initializable {
    @FXML
    private TableColumn<Event, String> eventNameColumn;
    @FXML
    private TableColumn<Event, String> eventDateColumn;
    @FXML
    private TableColumn<Event, String> eventLocationColumn;
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
        eventList.addAll(logic.getAllEvents());
        eventTable.setItems(eventList);
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    }
}
