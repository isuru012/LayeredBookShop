package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.ExpenditureModel;
import lk.ijse.bookshop.model.PaymentModel;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.Expenditure;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.EmployeeTm;
import lk.ijse.bookshop.view.tm.ExpenditureTm;
import lk.ijse.bookshop.view.tm.PaymentTm;
import tray.notification.NotificationType;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminExpenditureController {

    @FXML
    private TableView<ExpenditureTm> tblExpenditure;

    @FXML
    private TableColumn<?, ?> colExpenditureId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    static ObservableList observableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {

        observableList.clear();

        colExpenditureId.setCellValueFactory(new PropertyValueFactory<>("expenditure"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        ArrayList arrayList = ExpenditureModel.getAllDetails();
        for (Object payment : arrayList) {
            observableList.add(payment);
        }


        searchPart();

    }

    private void searchPart() {
        FilteredList<ExpenditureTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(expenditureTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (expenditureTm.getExpenditure().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(expenditureTm.getAmount()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (expenditureTm.getDescription().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<ExpenditureTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblExpenditure.comparatorProperty());
        tblExpenditure.setItems(sortedList);
    }

    @FXML
    void addOnAction(ActionEvent event) {
        try {
            String description = txtDescription.getText();
            double amount= Double.parseDouble(txtAmount.getText());
            Date date = Date.valueOf(LocalDate.now());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
            String nextExpenditureId=generateNextExpenditureId(ExpenditureModel.getCurrentId());
            String employeeId = LoginFormController.employeeId;

             String userName="ss";

            ExpenditureTm expenditureTm = new ExpenditureTm(nextExpenditureId,description,time,date,amount);

            colExpenditureId.setCellValueFactory(new PropertyValueFactory<>("expenditure"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            Expenditure expenditure = new Expenditure();

            boolean customerData = CustomerModel.insertCustomerData(customer);

            if (customerData) {
                Notification.notifie("Customer Data", "Customer Data Added", NotificationType.INFORMATION);
                observableList.add(customerTm);
                searchPart();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Added", NotificationType.ERROR);
            }




        }catch (SQLIntegrityConstraintViolationException exception){
            Notification.notifie("Alert","Data Already Exists",NotificationType.ERROR);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private String generateNextExpenditureId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("X");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id>=10){
                return "X000" + id;
            }else if(id>=100){
                return "X00" +id;
            }else if(id>=1000){
                return "X0"+id;
            }else if(id>=10000){
                return "X"+id;
            }else{

                return "X0000" + id;
            }
        }
        return "X00001";
    }

    @FXML
    void amountOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void descriptionOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void tblExpenditureOnMouseClick(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
