package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.PlaceOrderModel;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import org.controlsfx.control.textfield.TextFields;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import tray.notification.NotificationType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class CashierPlaceOrderController {

    public JFXComboBox cmbBatchNumber;

    public ComboBox jComboBox;
    public JFXTextField txtDescription;
    public Label lblItemCode;
    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox<?> cmbItemCode;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQtyOnHand;

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
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;


    static ObservableList observableList = CashierCustomersController.observableList;
    static String CusId = CashierCustomersController.CusId;

    public void initialize() throws SQLException, ClassNotFoundException {
        observableList.clear();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        ArrayList arrayList = CustomerModel.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }
        /*tblCustomer.setItems(observableList);*/

        searchPart();

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderId.setText(generateNextOrderId(PlaceOrderModel.getOrderId()));
        ArrayList loadAllDescriptionIds = PlaceOrderModel.loadAllDescriptionIds();
        TextFields.bindAutoCompletion(txtDescription,loadAllDescriptionIds);



    }

    private String generateNextOrderId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("O0");
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "O0" + id;
        }
        return "O01";
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

    private String generateNextCustomeId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("C0");
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "C0" + id;
        }
        return "C01";
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }


    public void onMouseClicked(MouseEvent mouseEvent) {
        CustomerTm tm = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex());
        txtName.setText(tm.getName());
        txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
        CusId = tm.getCode();
        lblCustomerName.setText(txtName.getText());
    }

    public void updateCartOnAction(ActionEvent actionEvent) {

    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (tblCustomer.getSelectionModel().getSelectedIndex() != -1) {
            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());


            boolean updateCustomer = CustomerModel.updateCustomer(name, phoneNumber, CusId);
            if (updateCustomer) {
                Notification.notifie("Customer Data", "Customer Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Updated", NotificationType.ERROR);
            }
        }
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();
        int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
        String cusId = generateNextCustomeId(CustomerModel.getOrderId());
        /*LocalDate date= LocalDate.now();*/
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String employeeId = LoginFormController.employeeId;

        Customer customer = new Customer(cusId, name, phoneNumber, date, employeeId);
        boolean customerData = CustomerModel.insertCustomerData(customer);
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

        observableList.add(customerTm);
        searchPart();
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void cmbBatchOnAction(ActionEvent actionEvent) {
    }


    public void keyReleasedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String text=txtDescription.getText();
        Item item = PlaceOrderModel.searchDescription(text);
        if (item!=null){
            lblItemCode.setText(item.getItemId());
            lblUnitPrice.setText(String.valueOf(item.getSellingUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(item.getQuantityOnHand()));
        }
    }
}
