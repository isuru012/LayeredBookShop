package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bookshop.model.UserCreationModel;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.WindowControll;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.SQLException;

public class TrueResetPasswordController {

    
    public AnchorPane pane;
    public Label lblBack;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtPassword2;
    public JFXTextField txtUsername;


    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(root));
    }

    public void resetPasswordOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        try{
            if(txtUsername.getText().equals("") && txtPassword.getText().equals("") && txtPassword2.getText().equals("") ){
                Notification.notifie("Password Reset","Fill All Fields First", NotificationType.ERROR);

            }else {
                if (!txtPassword.getText().equals("") && !txtPassword2.getText().equals("")) {
                    if (txtPassword.getText().equals(txtPassword2.getText())) {
                        boolean passwordReset = UserCreationModel.passwordReset(txtUsername.getText(), txtPassword.getText());
                        if (passwordReset) {
                            Notification.notifie("Password Reset", "Password Reseted Successfully", NotificationType.NOTICE);
                            Stage stage = (Stage) pane.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
                            stage.setScene(new Scene(root));
                        } else {
                            Notification.notifie("Password Reset", "Password Reset Failed", NotificationType.ERROR);
                        }
                    } else {
                        Notification.notifie("Password Reset", "Password Fields Not Matching", NotificationType.ERROR);
                    }
                }else{
                    Notification.notifie("Password Reset","Fill All Fields First", NotificationType.ERROR);
                }
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null,actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane,actionEvent);
    }
}
