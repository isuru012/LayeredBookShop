package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.UserCreationModel;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;
import lk.ijse.bookshop.view.tm.CustomerTm;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class CashierWindowController {

    public JFXButton btnminimize;
    ArrayList<JFXButton> arrayListButton = new ArrayList<>();
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblNameSet;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    public JFXButton btnCustomers;
    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private JFXButton btnPlaceReload;
    @FXML
    private JFXButton btnItems;
    @FXML
    private AnchorPane pane2;
    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colJoinedDate;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtSearch;
    Thread t1;
    private static boolean bool = false;

    public void initialize() throws SQLException, ClassNotFoundException {

        lblNameSet.setText(UserCreationModel.getEmployeeName(LoginFormController.employeeId));
        CashierCustomersController.observableList.clear();
        btnCustomers.setTextFill(Paint.valueOf("#0aa119"));
        btnCustomers.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
        lblDate.setText(String.valueOf(LocalDate.now()));
        t1 = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!bool) {
                try {
                    t1.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                final String timeNow = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timeNow);
                });
            }

        });
        t1.start();

        arrayListButton.add(btnCustomers);
        arrayListButton.add(btnPlaceOrder);
        arrayListButton.add(btnPlaceReload);
        arrayListButton.add(btnItems);

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        ArrayList arrayList = CustomerModel.getAllDetails();
        for (Object customerTm : arrayList) {
            CashierCustomersController.observableList.add(customerTm);
        }
        /*tblCustomer.setItems(observableList);*/

        searchPart();

    }

    private void searchPart() {

        FilteredList<CustomerTm> filteredList = new FilteredList(CashierCustomersController.observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customerTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (customerTm.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (customerTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(customerTm.getPhoneNumber()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<CustomerTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblCustomer.comparatorProperty());
        tblCustomer.setItems(sortedList);
    }

    @FXML
    void onMouseClicked(MouseEvent event) {

        try{

            CustomerTm tm = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex());
            txtName.setText(tm.getName());
            txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
            CashierCustomersController.CusId = tm.getCode();
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
            String cusId = generateNextCustomeId(CustomerModel.getOrderId());
            /*LocalDate date= LocalDate.now();*/
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String employeeId = LoginFormController.employeeId;

            CustomerDTO customerDTO = new CustomerDTO(cusId, name, phoneNumber, date, employeeId);
            boolean customerData = CustomerModel.insertCustomerData(customerDTO);
            if (customerData) {
                Notification.notifie("Customer Data", "Customer Data Added", NotificationType.INFORMATION);
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Added", NotificationType.ERROR);
            }

            CustomerTm customerTm = new CustomerTm(cusId, name, phoneNumber, date);
            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

            CashierCustomersController.observableList.add(customerTm);
            searchPart();
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    private String generateNextCustomeId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("C");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "C000" + id;
            }else if(id>=100){
                return "C00" +id;
            }else if(id>=1000){
                return "C0"+id;
            }else if(id>=10000){
                return "C"+id;
            }else{

                return "C0000" + id;
            }
        }
        return "C00001";
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Customer Data");
        alert.setContentText("Do you want to delete customer " + txtName.getText() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean deleteCustomer = CustomerModel.deleteCustomer(CashierCustomersController.CusId);
            if (deleteCustomer) {
                txtName.setText("");
                txtPhoneNumber.setText("");
                Notification.notifie("Customer Data", "Customer Data Deleted", NotificationType.INFORMATION);
                CashierCustomersController.observableList.clear();
                initialize();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Deleted", NotificationType.ERROR);
            }
        }

    }catch (Exception exception){
        Notification.notifie("Error",""+exception,NotificationType.ERROR);
    }
    }


    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());


            boolean updateCustomer = CustomerModel.updateCustomer(name, phoneNumber, CashierCustomersController.CusId);
            if (updateCustomer) {
                Notification.notifie("Customer Data", "Customer Data Updated", NotificationType.INFORMATION);
                CashierCustomersController.observableList.clear();
                initialize();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Updated", NotificationType.ERROR);
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }


    public void checkButton(JFXButton button) {
        for (int i = 0; i < arrayListButton.size(); i++) {
            if (arrayListButton.get(i) == button) {
                button.setTextFill(Paint.valueOf("#0aa119"));

            } else if(i==1){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnPlaceOrder.setStyle(null);
            }else if(i==2){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnPlaceReload.setStyle(null);
            }
            else {
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnCustomers.setStyle(null);

            }
        }
    }

    public void checkfocus() {
        if (btnCustomers.isFocused()) {

            btnCustomers.getStyleClass().add("btnPlaceOrder");
            checkButton(btnCustomers);

        } else if (btnPlaceOrder.isFocused()) {

            btnPlaceOrder.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
            checkButton(btnPlaceOrder);

        } else if (btnPlaceReload.isFocused()) {
            btnPlaceReload.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
            checkButton(btnPlaceReload);

        } else if (btnItems.isFocused()) {
            btnItems.getStyleClass().add("btnPlaceOrder");
            checkButton(btnItems);
        }
    }

    @FXML
    void customersOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERCUSTOMER, pane2);

    }

    @FXML
    void itemsOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERITEMS, pane2);

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERPLACEORDER, pane2);
    }

    @FXML
    void placeReloadOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERMAKERELOAD, pane2);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        t1.stop();
        WindowControll.window(null, actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }


    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);
    }
}
