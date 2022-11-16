package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;

public class TrueResetPasswordController {
    public JFXTextField txtUsername1;
    public JFXTextField txtPassword1;
    public JFXTextField txtPassword2;
    public AnchorPane pane;

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PASSWORDRESET,pane);
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
