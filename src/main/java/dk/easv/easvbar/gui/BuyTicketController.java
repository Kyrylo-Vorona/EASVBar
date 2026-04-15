package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.Ticket;
import dk.easv.easvbar.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BuyTicketController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<Ticket> ticketTypeBox;

    private Logic logic = Logic.getInstance();
    private OpenView openview = new OpenView();
    private Event currentEvent;

    public void loadIntoCombobox(Event event) throws EventException {
        this.currentEvent = event;

        List<Ticket> tickets = logic.getTicketForEvent(event.getId());
        ObservableList<Ticket> options = FXCollections.observableArrayList(tickets);
        ticketTypeBox.setItems(options);

        ticketTypeBox.setConverter(new StringConverter<Ticket>() {
            @Override
            public String toString(Ticket ticket) {
                return ticket == null ? "" : ticket.getName() + " - " + (int)ticket.getPrice() + " DKK";
            }
            @Override
            public Ticket fromString(String string) { return null; }
        });

        if (!options.isEmpty()) {
            ticketTypeBox.getSelectionModel().selectFirst();
        }
    }

    @FXML
    public void onBuyTicketClick(ActionEvent actionEvent) {
        String customerName = nameField.getText();
        String customerEmail = emailField.getText();
        Ticket selectedTicket = ticketTypeBox.getSelectionModel().getSelectedItem();
        if (customerName.isEmpty() || customerEmail.isEmpty() || selectedTicket == null) {
            OpenView.showErrorAlert("Please fill in all fields!");
            return;
        }
        if (!logic.isValidEmail(customerEmail)) {
            OpenView.showErrorAlert("The email address is invalid! Please check if it contains '@' and '.'");
            return;
        }
        try {
            String uniqueCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            logic.sellTicket(selectedTicket.getId(), customerName, customerEmail, uniqueCode);
            generateTicketImage(currentEvent, selectedTicket, customerName, uniqueCode);
            onCancelClick(actionEvent);
        } catch (Exception e) {
            OpenView.showErrorAlert("Purchase failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // The ticket image is generated and saved on your desktop.
    public void generateTicketImage(Event event, Ticket type, String customerName, String uniqueCode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/TicketCardView.fxml"));
            VBox ticketNode = loader.load();
            ((Label) ticketNode.lookup("#lblEventName")).setText(event.getName());
            ((Label) ticketNode.lookup("#lblCustomer")).setText("Customer: " + customerName);
            ((Label) ticketNode.lookup("#lblType")).setText("Type: " + type.getName());
            ((Label) ticketNode.lookup("#lblUniqueCode")).setText("UUID: " + uniqueCode);
            ((Label) ticketNode.lookup("#lblDate")).setText("Start: " + event.getStartTime());

            ImageView qrCodeImage = (ImageView) ticketNode.lookup("#imgQRCode");
            String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + uniqueCode;
            qrCodeImage.setImage(new Image(qrUrl, false));
            javafx.scene.Scene dummyScene = new javafx.scene.Scene(ticketNode);
            ticketNode.applyCss();
            ticketNode.layout();

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(javafx.scene.paint.Color.WHITE);
            WritableImage image = ticketNode.snapshot(params, null);
            File file = new File(System.getProperty("user.home") + "/Desktop/Ticket_" + uniqueCode + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCancelClick(ActionEvent event) {
        try {
            openview.openView("/dk/easv/easvbar/gui/MainView.fxml", event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }
}
