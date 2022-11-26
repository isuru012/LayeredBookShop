package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean updateStock(ArrayList<CustomerOrderDetail> customerOrderDetails) throws SQLException, ClassNotFoundException {
        for (CustomerOrderDetail detail : customerOrderDetails) {
            if (!updateItem(detail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateItem(CustomerOrderDetail detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().prepareStatement("UPDATE item SET QuantityOnHand=QuantityOnHand-? WHERE ItemId=?");
        stm.setObject(1, detail.getQuantity());
        stm.setObject(2, detail.getItemId());

        return stm.executeUpdate() > 0;
    }
}
