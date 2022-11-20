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
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;

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
    private JFXTextField txtSalary;

    @FXML
    void backOnAction(MouseEvent event) throws IOException {
        /*Navigation.navigate(Routes.LOGIN, pane);*/
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void createAccountOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CREATEACCOUNT, pane);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null,actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane,actionEvent);
    }
}
