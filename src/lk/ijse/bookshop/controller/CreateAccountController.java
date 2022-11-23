package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bookshop.model.UserCreationModel;
import lk.ijse.bookshop.to.User;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.WindowControll;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountController {

    public AnchorPane pane;
    public Label lblBack;
    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddess;

    @FXML
    private JFXTextField txtPhoneNumber;


    @FXML
    void backOnAction(MouseEvent event) throws IOException {
        /*Navigation.navigate(Routes.LOGIN, pane);*/
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void createAccountOnAction(ActionEvent event) throws IOException {
        if (!(txtUsername.getText().equals("")) && !(txtPassword.getText().equals("")) && !(txtName.getText().equals("")) && !(txtAddess.getText().equals("")) && !(txtPhoneNumber.getText().equals(""))) {
            User user = new User();
            user.setUserName(txtUsername.getText());
            user.setPassword(txtPassword.getText());
            user.setRole("Employee");


            try {
                boolean isAdded = UserCreationModel.userAllDetailSave(user);
                if (isAdded) {
                    Notification.notifie("User Creation", "User Added", NotificationType.INFORMATION);
                } else {
                    Notification.notifie("User Creation", "User Not Added", NotificationType.ERROR);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Notification.notifie("User Creation", "Enter All details first", NotificationType.ERROR);
        }

    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }
}
