package lk.ijse.bookshop.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case LOGIN:
                initUI("LoginForm.fxml");
                break;
            case PASSWORDRESET:
                initUI("PasswordResetForm.fxml");
                break;
            case TRUERESET:
                initUI("TrueResetPassword.fxml");
                break;
            case CREATEACCOUNT:
                initUI("CreateAccount.fxml");
                break;
            case ADMINWINDOW:
                initUI("AdminWindow.fxml");
                break;
            case DASHBOARD:
                initUI("AdminDashboard.fxml");
                break;
            case EMPLOYEE:
                initUI("AdminEmployee.fxml");
                break;
            case ADMINITEMS:
                initUI("AdminItems.fxml");
                break;
            case SUPPLIERORDER:
                initUI("AdminSupplier.fxml");
                break;
            case SUPPLIERDETAILS:
                initUI("AdminSupplierDetails.fxml");
                break;
            case OFFERS:
                initUI("AdminOffers.fxml");
                break;
            case PAYMENT:
                initUI("AdminPayment.fxml");
                break;
            case EXPENDITURE:
                initUI("AdminExpenditure.fxml");
                break;
            case CASHIERWINDOW:
                initUI("CashierWindow.fxml");
                break;
            case CASHIERCUSTOMER:
                initUI("CashierCustomers.fxml");
                break;
            case CASHIERPLACEORDER:
                initUI("CashierPlaceOrder.fxml");
                break;
            case CASHIERMAKERELOAD:
                initUI("CashierMakeReload.fxml");
                break;
            case CASHIERITEMS:
                initUI("CashierItems.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getStylesheets().add(Navigation.class.getResource("../assests/css/style.css").toExternalForm());
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/bookshop/view/" + location)));
    }
}