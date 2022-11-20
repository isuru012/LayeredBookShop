package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bookshop.model.UserCreation;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.SQLException;

public class PasswordResetFormController {

    public AnchorPane pane;
    public JFXTextField txtUsername;
    public Label lblBack;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(root));
    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CREATEACCOUNT, pane);
    }

    public void continueOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        if (UserCreation.checkUsername(txtUsername.getText())) {
            if (UserCreation.role.equals("Admin")) {
                Notification.notifie("Password Reset", "Admin can't reset password", NotificationType.ERROR);
            } else {
                Navigation.navigate(Routes.TRUERESET, pane);
            }
        }else{
            Notification.notifie("Password Reset", "Incorrect Username", NotificationType.ERROR);
        }

    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }
}
