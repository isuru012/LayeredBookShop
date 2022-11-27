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
import lk.ijse.bookshop.model.EmployeeModel;
import lk.ijse.bookshop.model.UserCreationModel;
import lk.ijse.bookshop.to.Employee;
import lk.ijse.bookshop.to.User;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.WindowControll;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.SQLException;

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
    void backOnAction(MouseEvent event) throws IOException {
        /*Navigation.navigate(Routes.LOGIN, pane);*/
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void createAccountOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (!(txtUsername.getText().equals("")) && !(txtPassword.getText().equals("")) &&
                !(txtName.getText().equals("")) && !(txtAddess.getText().equals("")) &&
                !(txtPhoneNumber.getText().equals(""))) {
            User user = new User();
            user.setUserName(txtUsername.getText());
            user.setPassword(txtPassword.getText());
            user.setRole("Employee");
            Employee employee=new Employee();
            employee.setEmployeeId(generateNextEmployeeId(EmployeeModel.currentEmployeeId()));
            employee.setAddress(txtAddess.getText());
            employee.setName(txtName.getText());
            employee.setSalary(0);
            employee.setPhoneNumber(Integer.parseInt(txtPhoneNumber.getText()));
            employee.setUserName(txtUsername.getText());

            try {
                boolean isAdded = UserCreationModel.userAllDetailSave(user,employee);
                if (isAdded) {
                    Notification.notifie("User Creation", "User Added", NotificationType.INFORMATION);
                } else {
                    Notification.notifie("User Creation", "User Not Added", NotificationType.ERROR);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Notification.notifie("User Creation", "Enter All details first", NotificationType.ERROR);
        }

    }

    private String generateNextEmployeeId(String currentEmployeeId) {
        if (currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("E");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id>=10){
                return "E000" + id;
            }else if(id>=100){
                return "E00" +id;
            }else if(id>=1000){
                return "E0"+id;
            }else if(id>=10000){
                return "E"+id;
            }else{

                return "E0000" + id;
            }
        }
        return "E00001";
    }


    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }
}
