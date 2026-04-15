package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import dk.easv.easvbar.be.SoldTicket;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SoldTicketsController implements Initializable {
    @FXML
    private TableView<SoldTicket> tblSoldTickets;
    @FXML
    private TableColumn<SoldTicket, String> colName;
    @FXML private TableColumn<SoldTicket, String> colEmail;
    @FXML private TableColumn<SoldTicket, String> colType;
    @FXML private TableColumn<SoldTicket, String> colCode;

    private Event currentEvent;
    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("uniqueCode"));
    }

    public void setEvent(Event event) {
        this.currentEvent = event;
        loadSoldTickets();
    }

    private void loadSoldTickets() {
        try {
            List<SoldTicket> sold = logic.getSoldTicketsForEvent(currentEvent.getId());
            tblSoldTickets.setItems(FXCollections.observableArrayList(sold));
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    @FXML
    private void onClose(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/CoordinatorEventManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
