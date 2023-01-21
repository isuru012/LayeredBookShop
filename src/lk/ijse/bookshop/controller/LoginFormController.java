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
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.LoginFormBO;
import lk.ijse.bookshop.model.UserCreationModel;
import lk.ijse.bookshop.dto.UserDTO;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;
import tray.notification.NotificationType;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class LoginFormController {
    public JFXTextField txtUsername;

    public AnchorPane pane;
    public JFXButton btnminimize;
    public JFXPasswordField txtPasswordField;
    public AnchorPane pane2;
    public static String employeeId;
    static boolean boolUsername=false;
    static boolean boolPassword=false;

    LoginFormBO getLoginData= (LoginFormBO) BOFactory.getBOFactory().getBOTypes(BOFactory.BOTypes.LOGINFORM);

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
        signInOnACtion();
//        Navigation.navigate(Routes.CASHIERWINDOW,pane);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }

    public void signInOnEnterKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException, IOException {
        Pattern pattern2 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$");
        Matcher matcher2 = pattern2.matcher(txtPasswordField.getText());

        boolPassword = matcher2.matches();
        if (boolPassword) {
            txtPasswordField.setFocusColor(BLUE);
        }else{
            txtPasswordField.setFocusColor(RED);
        }

        if (keyEvent.getCode()== KeyCode.ENTER){
            signInOnACtion();
        }
    }

    private void signInOnACtion() throws SQLException, ClassNotFoundException, IOException {

        if (!txtUsername.getText().equals("") & !txtPasswordField.getText().equals("")) {
            if (boolUsername) {
                if (boolPassword) {
                    UserDTO userDTO = getLoginData.getLoginData(txtUsername.getText(), txtPasswordField.getText());

                    if (userDTO != null) {
                        if (userDTO.getUserName().equals(txtUsername.getText()) && userDTO.getPassword().equals(txtPasswordField.getText()) && userDTO.getRole().equals("Admin")) {
                            Navigation.navigate(Routes.ADMINWINDOW, pane);
                        } else if (userDTO.getUserName().equals(txtUsername.getText()) && userDTO.getPassword().equals(txtPasswordField.getText()) && userDTO.getRole().equals("Employee")) {
                            employeeId = getLoginData.getEmployeeId(userDTO.getUserName());
                            Navigation.navigate(Routes.CASHIERWINDOW, pane);

                        }
                    } else {
                        Notification.notifie("Login In", "Incorrect Username Or Password", NotificationType.ERROR);
                    }
                }
            }
        }else{
            Notification.notifie("Login In", "Enter All Fields First", NotificationType.ERROR);

        }
    }

    public void gogleOnAction(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/"));
    }

    public void facebookOnAction(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/"));
    }

    public void instagramOnAction(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.instagram.com/"));
    }


    public void usernameKeyReleased(KeyEvent keyEvent) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");
        Matcher matcher = pattern.matcher(txtUsername.getText());

        boolUsername = matcher.matches();
        if (boolUsername) {
            txtUsername.setFocusColor(BLUE);
        }else{
            txtUsername.setFocusColor(RED);
        }
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtPasswordField.requestFocus();
        }
    }
}
