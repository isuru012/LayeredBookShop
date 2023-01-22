package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.CashierCustomerBO;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import tray.notification.NotificationType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class CashierCustomersController {
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

    public static String CusId;
    public static ObservableList observableList = FXCollections.observableArrayList();

    CashierCustomerBO cashierCustomerBO= (CashierCustomerBO) BOFactory.getBOFactory().getBOTypes(BOFactory.BOTypes.CASHIERCUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        observableList.clear();

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        ArrayList arrayList = cashierCustomerBO.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }


        searchPart();


    }

    private void searchPart() {
        FilteredList<CustomerTm> filteredList = new FilteredList(observableList, b -> true);

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
    void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{

            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
            String cusId = generateNextCustomeId(cashierCustomerBO.getOrderId());
            /*LocalDate date= LocalDate.now();*/
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String employeeId = LoginFormController.employeeId;

            CustomerTm customerTm = new CustomerTm(cusId, name, phoneNumber, date);
            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

            CustomerDTO customerDTO = new CustomerDTO(cusId, name, phoneNumber, date, employeeId);

            boolean customerData = cashierCustomerBO.insertCustomerData(customerDTO);

            if (customerData) {
                Notification.notifie("Customer Data", "Customer Data Added", NotificationType.INFORMATION);
                observableList.add(customerTm);
                searchPart();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Added", NotificationType.ERROR);
            }
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
            alert.setContentText("Do you want to delete customer "+txtName.getText()+"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean deleteCustomer = cashierCustomerBO.deleteCustomer(CusId);
                if (deleteCustomer) {
                    txtName.setText("");
                    txtPhoneNumber.setText("");
                    Notification.notifie("Customer Data", "Customer Data Deleted", NotificationType.INFORMATION);
                    observableList.clear();
                    initialize();
                } else {
                    Notification.notifie("Customer Data", "Customer Data  Not Updated", NotificationType.ERROR);
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

            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setCusId(CusId);
            customerDTO.setName(name);
            customerDTO.setPhoneNumber(phoneNumber);
            boolean updateCustomer = cashierCustomerBO.updateCustomer(customerDTO);
            if (updateCustomer) {
                Notification.notifie("Customer Data", "Customer Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Updated", NotificationType.ERROR);
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }


    }

    public void searchOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        /*String text=txtSearch.getText();
        Customer customer= CustomerModel.search(text);
        if (customer!=null){
            txtName.setText(customer.getName());
            txtPhoneNumber.setText(String.valueOf(customer.getPhoneNumber()));
        }*/
    }

    public void onMouseClicked(MouseEvent mouseEvent) {

        try{

            CustomerTm tm = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex());
            txtName.setText(tm.getName());
            txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
            CusId = tm.getCode();
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }
}
