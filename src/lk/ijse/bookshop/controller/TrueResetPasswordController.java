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
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;

public class TrueResetPasswordController {
    public JFXTextField txtUsername1;
    public JFXTextField txtPassword1;
    public JFXTextField txtPassword2;
    public AnchorPane pane;
    public Label lblBack;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(root));
    }

    public void resetPasswordOnAction(ActionEvent actionEvent) {

    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null,actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane,actionEvent);
    }
}
