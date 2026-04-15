package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.Ticket;
import dk.easv.easvbar.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorTicketManagementController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private TableView<Ticket> tblTicketTypes;
    @FXML
    private TableColumn<Ticket, String> colType;
    @FXML
    private TableColumn<Ticket, Double> colPrice;
    @FXML
    private TableColumn<Ticket, String> colDescription;
    private ObservableList<Ticket> ticketsList;
    private Event currentEvent;

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

    public void openCreateTicketView(ActionEvent event) {
        try {
            FXMLLoader loader = openview.openView("/dk/easv/easvbar/gui/CreateTicketView.fxml", event);
            CreateTicketController controller = loader.getController();
            controller.setLabel(currentEvent);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void openEditTicketView(ActionEvent event) {
        Ticket selectedTicket = tblTicketTypes.getSelectionModel().getSelectedItem();
        if (selectedTicket != null) {
            try {
                FXMLLoader loader = openview.openView("/dk/easv/easvbar/gui/CreateTicketView.fxml", event);
                CreateTicketController controller = loader.getController();
                controller.setTicketData(selectedTicket, currentEvent);
            } catch (EventException e) {
                OpenView.showErrorAlert(e.getMessage());
            }
        }else {
            OpenView.showErrorAlert("Please select a ticket to go");
        }
    }

    public void deleteTicket (ActionEvent event) {
        Ticket selected = tblTicketTypes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                logic.deleteTicket(selected);
                readDataIntoList();
            } catch (EventException e) {
                OpenView.showErrorAlert(e.getMessage());
            }
        }
    }

    public void backToEventManagement(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/CoordinatorEventManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void setLabel(Event selected) {
        this.currentEvent = selected;
        label.setText("Ticket management - " + selected.getName());
        try {
            readDataIntoList();
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colType.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void readDataIntoList() throws EventException {
        ticketsList = FXCollections.observableArrayList();
        ticketsList.addAll(logic.getTicketForEvent(currentEvent.getId()));
        tblTicketTypes.setItems(ticketsList);
    }
}
