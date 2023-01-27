package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.AdminExpenditureBO;

import lk.ijse.bookshop.dto.ExpenditureDTO;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.ExpenditureTm;
import tray.notification.NotificationType;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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
    AdminExpenditureBO adminExpenditureBO= (AdminExpenditureBO) BOFactory.getBOFactory().getBOTypes(BOFactory.BOTypes.EXPENDITURE);
    public void initialize() throws SQLException, ClassNotFoundException {
        Platform.runLater(() -> txtDescription.requestFocus());
        observableList.clear();

        colExpenditureId.setCellValueFactory(new PropertyValueFactory<>("expenditure"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        ArrayList arrayList = adminExpenditureBO.getAllDetails();
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
    void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        addToTblExpenditure();

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            String desCriptionText = txtDescription.getText();
            double buyingPrice= Double.parseDouble(txtAmount.getText());
            String expenditureId= String.valueOf(colExpenditureId.getCellData
                    (tblExpenditure.getSelectionModel().getSelectedIndex()));

            ExpenditureDTO expenditureDTO=new ExpenditureDTO();
            expenditureDTO.setExpenditure(expenditureId);
            expenditureDTO.setAmount(buyingPrice);
            expenditureDTO.setDescription(desCriptionText);

            boolean updateExpenditure = adminExpenditureBO.updateExpenditure(expenditureDTO);
            if (updateExpenditure) {
                Notification.notifie("Expenditure Data", "Expenditure Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Expenditure Data", "Expenditure Data  Not Updated", NotificationType.ERROR);
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Expenditure Data");
            alert.setContentText("Do you want to delete Expenditure "+txtDescription.getText()+"?");

            String expenditureId= String.valueOf(colExpenditureId.getCellData(tblExpenditure.getSelectionModel().getSelectedIndex()));


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                boolean deleteExpenditure = adminExpenditureBO.deleteExpenditure(expenditureId);
                if (deleteExpenditure) {
                    txtDescription.setText("");
                    txtAmount.setText("");
                    txtSearch.setText("");
                    Notification.notifie("Expenditure Data", "Expenditure Data Deleted", NotificationType.INFORMATION);
                    observableList.clear();
                    initialize();
                } else {
                    Notification.notifie("Expenditure Data", "Expenditure Data  Not Deleted", NotificationType.ERROR);
                }
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    private void addToTblExpenditure() throws SQLException, ClassNotFoundException {

        /*try{*/
            String description = txtDescription.getText();
            double amount= Double.parseDouble(txtAmount.getText());
            Date date = Date.valueOf(LocalDate.now());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
            String nextExpenditureId=generateNextExpenditureId(adminExpenditureBO.getCurrentId());


            String userName=adminExpenditureBO.getUsername();

            ExpenditureTm expenditureTm = new ExpenditureTm(nextExpenditureId,description,time,date,amount);

            ExpenditureDTO expenditureDTO = new ExpenditureDTO(nextExpenditureId,description,amount,date,time,userName);

            boolean expenditureData = adminExpenditureBO.insertExpenditureData(expenditureDTO);

            if (expenditureData) {
                Notification.notifie("Expenditure Data", "Expenditure Data Added", NotificationType.INFORMATION);
                observableList.add(expenditureTm);
                searchPart();
            } else {
                Notification.notifie("Expenditure Data", "Expenditure Data  Not Added", NotificationType.ERROR);
            }

       /* }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }*/


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
    void amountOnKeyReleased(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode()== KeyCode.ENTER){
            addToTblExpenditure();
            txtDescription.requestFocus();
        }
        btnAdd.setDisable(false);
    }

    @FXML
    void descriptionOnKeyReleased(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtAmount.requestFocus();
        }
        btnAdd.setDisable(false);
    }

    @FXML
    void tblExpenditureOnMouseClick(MouseEvent event) {

        try{
            ExpenditureTm tm = tblExpenditure.getItems().get(tblExpenditure.getSelectionModel().getSelectedIndex());
            txtAmount.setText(String.valueOf(tm.getAmount()));
            txtDescription.setText(String.valueOf(tm.getDescription()));
            btnAdd.setDisable(true);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }
    private void clearTableSelection() {
        if (!tblExpenditure.isFocused()) {
            tblExpenditure.getSelectionModel().clearSelection();
        }
    }

}
