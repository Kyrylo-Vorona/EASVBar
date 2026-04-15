package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.Ticket;
import dk.easv.easvbar.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.List;

public class BuyTicketController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<Ticket> ticketTypeBox;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void loadIntoCombobox(Event event) throws EventException {
        List<Ticket> tickets = logic.getTicketForEvent(event.getId());
        ObservableList<Ticket> options = FXCollections.observableArrayList(tickets);
        ticketTypeBox.setItems(options);
        ticketTypeBox.setConverter(new StringConverter<Ticket>() {
            @Override
            public String toString(Ticket ticket) {
                return ticket == null ? "" : ticket.getName() + " - " + ticket.getPrice() + " DKK";
            }
            @Override
            public Ticket fromString(String string) {
                return null;
            }
        });

        if (!options.isEmpty()) {
            ticketTypeBox.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void onBuyTicketClick(ActionEvent actionEvent) {
        String customerName = txtName.getText();
        String customerEmail = txtEmail.getText();
        Ticket selectedTicket = ticketTypeBox.getSelectionModel().getSelectedItem();
        if (customerName.isEmpty() || customerEmail.isEmpty() || selectedTicket == null) {
            OpenView.showErrorAlert("Please fill in all fields and select a ticket!");
            return;
        }
        try {
            logic.sellTicket(selectedTicket.getId(), customerName, customerEmail);
            onCancelClick(actionEvent);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
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
