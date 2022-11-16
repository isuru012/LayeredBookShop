package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;

public class CreateAccountController {

    public AnchorPane pane;
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
        Navigation.navigate(Routes.LOGIN, pane);
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
