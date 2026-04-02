package dk.easv.easvbar.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/dk/easv/easvbar/gui/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 450);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Event ticket system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
