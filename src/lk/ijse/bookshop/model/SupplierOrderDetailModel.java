package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.SupplierOrderDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailModel {
   /* public static boolean saveOrderDetails(ArrayList<SupplierOrderDetailsDTO> supplierOrderDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetailsDTO ord : supplierOrderDetailsDTOS) {
            if (!addOrderDetail(ord)) {
                return false;
            }
        }
        return true;
    }
    private static boolean addOrderDetail(SupplierOrderDetailsDTO ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT " +
                "INTO suporderdetails values(?,?,?,?,?)");

        statement.setObject(1, ord.getSupOrderId());
        statement.setObject(2, ord.getItemId());
        statement.setObject(3, ord.getUnitPrice());
        statement.setObject(4, ord.getQuantity());
        statement.setObject(5, ord.getTotalPrice());

        return statement.executeUpdate() > 0;
    }*/
}
