package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.bll.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // to log in as an admin: username "admin" ; password "admin123"
    // to log in as a coordinator: username "Coordinator1" ; password "coordinator123"

    @FXML
    private VBox eventContainer;

    private Logic logic = Logic.getInstance();
    OpenView openview = new OpenView();

    public void initialize(URL location, ResourceBundle resources) {
        loadEvents();
    }

    private void addEventCard(Event e) {
        VBox card = new VBox(5);
        card.getStyleClass().add("event-card");
        HBox topRow = new HBox();
        Label title = new Label(e.getName());
        title.getStyleClass().add("event-title");
        Label dateLabel = new Label(e.getStartTime());
        dateLabel.getStyleClass().add("event-date");
        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);
        topRow.getChildren().addAll(title, topSpacer, dateLabel);
        Label locationLabel = new Label(e.getLocation());
        locationLabel.getStyleClass().add("event-location");
        Label descLabel = new Label(e.getNotes());
        descLabel.setWrapText(true);
        descLabel.getStyleClass().add("event-description");
        HBox bottomRow = new HBox();
        bottomRow.setAlignment(Pos.CENTER_LEFT);
        Label priceLabel = new Label("Price: " + (int)e.getPrice() + " kr");
        priceLabel.getStyleClass().add("price-tag");
        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);
        Button buyButton = new Button("Buy ticket");
        buyButton.setOnAction(event -> {openBuyTicketWindow(event, e);});
        buyButton.getStyleClass().add("buy-button");
        bottomRow.getChildren().addAll(priceLabel, bottomSpacer, buyButton);
        card.getChildren().addAll(topRow, locationLabel, descLabel, bottomRow);
        eventContainer.getChildren().add(card);
    }

    private void loadEvents() {
        try {
            eventContainer.getChildren().clear();
            List<Event> events = logic.getAllEvents();
            for (Event e : events) {
                addEventCard(e);
            }
        } catch (Exception ex) {
            OpenView.showErrorAlert("Load error: " + ex.getMessage());
        }
    }

    @FXML
    private void onLoginClick(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/LoginView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    private void openBuyTicketWindow(ActionEvent event, Event selectedEvent) {
        try {
            String filepath = "/dk/easv/easvbar/gui/BuyTicketView.fxml";
            FXMLLoader loader = openview.openView(filepath, event);
            BuyTicketController controller = loader.getController();
            controller.loadIntoCombobox(selectedEvent);
        } catch (EventException ex) {
            OpenView.showErrorAlert("Error: " + ex.getMessage());
        }
    }
}

