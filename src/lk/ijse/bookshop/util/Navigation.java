package lk.ijse.bookshop.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class Navigation{
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case LOGIN:
                window.setTitle("Login ");
                initUI("LoginForm.fxml");
                break;
            case PASSWORDRESET:
                window.setTitle("Password Reset");
                initUI("PasswordResetForm.fxml");
                break;
            case TRUERESET:
                window.setTitle("Password Reset");
                initUI("TrueResetPassword.fxml");
                break;
            case CREATEACCOUNT:
                window.setTitle("Create Account");
                initUI("CreateAccount.fxml");
                break;
            case ADMINWINDOW:

                initUI("AdminWindow.fxml");
                break;
            case DASHBOARD:

                initUI("AdminDashboard.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/bookshop/view/" + location)));
    }
}