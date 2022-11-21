package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bookshop.model.UserCreation;
import lk.ijse.bookshop.to.User;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public JFXTextField txtUsername;

    public AnchorPane pane;
    public JFXButton btnminimize;
    public JFXPasswordField txtPasswordField;
    public AnchorPane pane2;

    public void forgotPasswordOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PASSWORDRESET, pane2);
    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CREATEACCOUNT, pane2);
    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (!txtUsername.getText().equals("") & !txtPasswordField.getText().equals("")) {
            User user = UserCreation.getLoginData(txtUsername.getText(), txtPasswordField.getText());

            if (user != null) {
                if (user.getUserName().equals(txtUsername.getText()) && user.getPassword().equals(txtPasswordField.getText()) && user.getRole().equals("Admin")) {
                    Navigation.navigate(Routes.ADMINWINDOW, pane);
                } else if (user.getUserName().equals(txtUsername.getText()) && user.getPassword().equals(txtPasswordField.getText()) && user.getRole().equals("Employee")) {
                    Navigation.navigate(Routes.CASHIERWINDOW, pane);
                }
            } else {
                Notification.notifie("Login In", "Incorrect Username Or Password", NotificationType.ERROR);
            }
        }else{
            Notification.notifie("Login In", "Enter All Fields First", NotificationType.ERROR);

        }
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }
}
