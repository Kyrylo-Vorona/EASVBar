package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenView {
    public void openView(String filepath, ActionEvent event) throws EventException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filepath));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            if (getClass().getResource("/css/style.css") != null) {
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            throw new EventException("Could not load the window: " + filepath, e);
        }
    }

    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
