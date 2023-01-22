package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.AdminEmployeeBO;
import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.entity.Employee;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.EmployeeTm;
import tray.notification.NotificationType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminEmployeeController {

    public TableView<EmployeeTm> tblEmployee;
    @FXML
    private AnchorPane pane2;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddess;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtSalary;
    ObservableList observableList = FXCollections.observableArrayList();

    AdminEmployeeBO adminEmployeeBO= (AdminEmployeeBO) BOFactory.getBOFactory().getBOTypes(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() throws SQLException, ClassNotFoundException {
        Platform.runLater(() -> txtName.requestFocus());
        observableList.clear();
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        ArrayList arrayList = adminEmployeeBO.getAllDetails();
        for (Object employeeTm : arrayList) {
            observableList.add(employeeTm);
        }
        tblEmployee.setItems(observableList);
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Employee Data");
            alert.setContentText("Do you want to delete Employee " + txtName.getText() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            String employeeId = String.valueOf(colEmployeeId.getCellData(tblEmployee.getSelectionModel().getSelectedIndex()));
            if (result.get() == ButtonType.OK) {
                boolean deleteEmployee = adminEmployeeBO.deleteEmployee(employeeId);
                if (deleteEmployee) {
                    txtName.setText("");
                    txtPhoneNumber.setText("");
                    txtSalary.setText("");
                    txtAddess.setText("");
                    Notification.notifie("Employee Data", "Employee Data Deleted", NotificationType.INFORMATION);
                    observableList.clear();
                    initialize();
                } else {
                    Notification.notifie("Employee Data", "Employee Data  Not Deleted", NotificationType.ERROR);
                }
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void onMouseClickOnEmployeeTable(MouseEvent event) {

        try{
            EmployeeTm tm = tblEmployee.getItems().get(tblEmployee.getSelectionModel().getSelectedIndex());
            txtName.setText(tm.getName());
            txtAddess.setText(tm.getAddress());
            txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
            txtSalary.setText(String.valueOf(tm.getSalary()));

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            String employeeId = String.valueOf(colEmployeeId.getCellData(tblEmployee.getSelectionModel().getSelectedIndex()));
            double salary = Double.parseDouble(txtSalary.getText());

            EmployeeDTO employeeDTO=new EmployeeDTO();
            employeeDTO.setEmployeeId(employeeId);
            employeeDTO.setSalary(salary);



            boolean updateCustomer = adminEmployeeBO.updateSalary(employeeDTO);
            if (updateCustomer) {
                Notification.notifie("Employee Data", "Employee Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Employee Data", "Employee Data  Not Updated", NotificationType.ERROR);
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

}
