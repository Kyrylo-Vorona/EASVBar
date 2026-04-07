package dk.easv.easvbar.gui;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUserManagementController implements Initializable {
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableView<User> userTable;
    private ObservableList<User> userList;
    private User selected;

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

    public void backToEventManagement(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AdminEventManagementView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void openAddUserView(ActionEvent event) {
        try {
            String filepath = "/dk/easv/easvbar/gui/AddUserView.fxml";
            openview.openView(filepath, event);
        } catch (EventException e) {
            OpenView.showErrorAlert(e.getMessage());
        }
    }

    public void openEditUserView(ActionEvent event) throws EventException {
        User selected = userTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            FXMLLoader loader = openview.openView("/dk/easv/easvbar/gui/AddUserView.fxml", event);
            AddUserController controller = loader.getController();
            controller.setUserData(selected);
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
        userList = FXCollections.observableArrayList();
        userList.addAll(logic.getAllUsers());
        userTable.setItems(userList);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
