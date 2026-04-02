package dk.easv.easvbar.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private VBox eventContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addEventCard("Rock Festival", "12/06/2026",
                "Spangsbjerg Kirkevej 103, 6700 Esbjerg, Innovatorium",
                "Come to the Rock Festival", 100);

        addEventCard("Wine Tasting", "20/06/2026",
                "Spangsbjerg Kirkevej 103, 6700 Esbjerg, Innovatorium",
                "Come to the Wine Tasting", 100);

        addEventCard("EASV Party", "01/07/2026",
                "Spangsbjerg Kirkevej 103, 6700 Esbjerg, Innovatorium",
                "Come to the EASV Party", 100);
    }

    private void addEventCard(String name, String date, String location, String description, double price) {
        VBox card = new VBox(5);
        card.getStyleClass().add("event-card");

        HBox topRow = new HBox();
        Label title = new Label(name);
        title.getStyleClass().add("event-title");

        Label dateLabel = new Label(date);
        dateLabel.getStyleClass().add("event-date");

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);
        topRow.getChildren().addAll(title, topSpacer, dateLabel);

        Label locationLabel = new Label(location);
        locationLabel.getStyleClass().add("event-location");

        Label descLabel = new Label(description);
        descLabel.setWrapText(true);
        descLabel.getStyleClass().add("event-description");

        HBox bottomRow = new HBox();
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        Label priceLabel = new Label("Price: " + (int)price + " kr");
        priceLabel.getStyleClass().add("price-tag");

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        Button buyButton = new Button("Buy ticket");
        buyButton.setOnAction(e -> {openBuyTicketWindow(e, name, price);});
        buyButton.getStyleClass().add("buy-button");

        bottomRow.getChildren().addAll(priceLabel, bottomSpacer, buyButton);

        card.getChildren().addAll(topRow, locationLabel, descLabel, bottomRow);
        eventContainer.getChildren().add(card);
    }

    @FXML
    private void onLoginClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/LoginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            if (getClass().getResource("/css/style.css") != null) {
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openBuyTicketWindow(ActionEvent event, String eventName, double price) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/easvbar/gui/BuyTicketView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            if (getClass().getResource("/css/style.css") != null) {
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

