package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;
import lk.ijse.bookshop.to.CustomerReloadDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReloadDetailModel {
    public static boolean saveReloadDetails(ArrayList<CustomerReloadDetail> customerReloadDetails) throws SQLException, ClassNotFoundException {
        for (CustomerReloadDetail ord : customerReloadDetails) {
            if (!addReloadDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private static boolean addReloadDetail(CustomerReloadDetail ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusreloaddetails values(?,?,?)");

        statement.setObject(1, ord.getCusReloadId());
        statement.setObject(2, ord.getReloadId());
        statement.setObject(3, ord.getTotalPrice());


        return statement.executeUpdate() > 0;
    }
}
