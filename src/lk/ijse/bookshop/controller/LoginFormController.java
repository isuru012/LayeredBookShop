package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import lk.ijse.bookshop.model.UserCreationModel;
import lk.ijse.bookshop.to.User;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;
import tray.notification.NotificationType;


import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.sql.SQLException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class LoginFormController {
    public JFXTextField txtUsername;

    public AnchorPane pane;
    public JFXButton btnminimize;
    public JFXPasswordField txtPasswordField;
    public AnchorPane pane2;
    public static String employeeId;
    public void initialize(){
        Platform.runLater(()->txtUsername.requestFocus());

    }

    public void forgotPasswordOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PASSWORDRESET, pane2);
    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CREATEACCOUNT, pane2);
    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (!txtUsername.getText().equals("") & !txtPasswordField.getText().equals("")) {
            User user = UserCreationModel.getLoginData(txtUsername.getText(), txtPasswordField.getText());

            if (user != null) {
                if (user.getUserName().equals(txtUsername.getText()) && user.getPassword().equals(txtPasswordField.getText()) && user.getRole().equals("Admin")) {
                    Navigation.navigate(Routes.ADMINWINDOW, pane);
                } else if (user.getUserName().equals(txtUsername.getText()) && user.getPassword().equals(txtPasswordField.getText()) && user.getRole().equals("Employee")) {
                    employeeId= UserCreationModel.getEmployeeId(user.getUserName());
                    Navigation.navigate(Routes.CASHIERWINDOW, pane);

                }
            } else {
                Notification.notifie("Login In", "Incorrect Username Or Password", NotificationType.ERROR);
            }
        }else{
            Notification.notifie("Login In", "Enter All Fields First", NotificationType.ERROR);

        }
//        Navigation.navigate(Routes.CASHIERWINDOW,pane);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }

    public void signInOnEnterKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException, IOException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            if (!txtUsername.getText().equals("") & !txtPasswordField.getText().equals("")) {
                User user = UserCreationModel.getLoginData(txtUsername.getText(), txtPasswordField.getText());

                if (user != null) {
                    if (user.getUserName().equals(txtUsername.getText()) && user.getPassword().equals(txtPasswordField.getText()) && user.getRole().equals("Admin")) {
                        Navigation.navigate(Routes.ADMINWINDOW, pane);
                    } else if (user.getUserName().equals(txtUsername.getText()) && user.getPassword().equals(txtPasswordField.getText()) && user.getRole().equals("Employee")) {
                        employeeId= UserCreationModel.getEmployeeId(user.getUserName());
                        Navigation.navigate(Routes.CASHIERWINDOW, pane);

                    }
                } else {
                    Notification.notifie("Login In", "Incorrect Username Or Password", NotificationType.ERROR);
                }
            }else{
                Notification.notifie("Login In", "Enter All Fields First", NotificationType.ERROR);

            }
        }
    }
}
