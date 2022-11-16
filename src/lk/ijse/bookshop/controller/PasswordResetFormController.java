package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;

public class PasswordResetFormController {

    public AnchorPane pane;
    public JFXTextField txtUsername;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void createAccountOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CREATEACCOUNT, pane);
    }

    public void continueOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TRUERESET,pane);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null,actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane,actionEvent);
    }
}
