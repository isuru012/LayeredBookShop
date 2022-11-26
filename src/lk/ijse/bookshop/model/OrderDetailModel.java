package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {
    public static boolean saveOrderDetails(ArrayList<CustomerOrderDetail> customerOrderDetails) throws SQLException, ClassNotFoundException {
        for (CustomerOrderDetail ord : customerOrderDetails) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private static boolean addOrderDetail(CustomerOrderDetail ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusorderdetails values(?,?,?,?,?)");

        statement.setObject(1, ord.getCusOrderId());
        statement.setObject(2, ord.getItemId());
        statement.setObject(3, ord.getUnitPrice());
        statement.setObject(4, ord.getQuantity());
        statement.setObject(5, ord.getTotalPrice());

        return statement.executeUpdate() > 0;
    }
}
