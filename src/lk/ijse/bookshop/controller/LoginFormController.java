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
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUsername;

    public AnchorPane pane;
    public JFXButton btnminimize;
    public JFXPasswordField txtPasswordField;

    public void forgotPasswordOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PASSWORDRESET, pane);
    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CREATEACCOUNT, pane);
    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUsername.getText().equals("admin")&& txtPasswordField.getText().equals("admin")){
            Navigation.navigate(Routes.ADMINWINDOW,pane);
        }else if (txtUsername.getText().equals("cashier")&& txtPasswordField.getText().equals("cashier")){
            Navigation.navigate(Routes.CASHIERWINDOW,pane);

        }
        else{
            Notification.notifie("Login In","Incorrect Username Or Password",NotificationType.ERROR);
        }

    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }
}
