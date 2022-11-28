package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;
import lk.ijse.bookshop.to.SupplierOrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailModel {
    public static boolean saveOrderDetails(ArrayList<SupplierOrderDetail> supplierOrderDetails) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetail ord : supplierOrderDetails) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }
    private static boolean addOrderDetail(SupplierOrderDetail ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO suporderdetails values(?,?,?,?,?)");

        statement.setObject(1, ord.getSupOrderId());
        statement.setObject(2, ord.getItemId());
        statement.setObject(3, ord.getUnitPrice());
        statement.setObject(4, ord.getQuantity());
        statement.setObject(5, ord.getTotalPrice());

        return statement.executeUpdate() > 0;
    }
}
