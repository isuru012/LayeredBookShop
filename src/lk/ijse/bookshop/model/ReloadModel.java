package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.CustomerOrderDetail;
import lk.ijse.bookshop.to.CustomerReloadDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReloadModel {
    public static boolean updateAmount(ArrayList<CustomerReloadDetail> customerReloadDetails) throws SQLException, ClassNotFoundException {
        for (CustomerReloadDetail detail : customerReloadDetails) {
            if (!updateAmmount(detail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateAmmount(CustomerReloadDetail detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().
                prepareStatement("UPDATE reload SET ReloadAmount=ReloadAmount-? WHERE ReloadId=?");
        stm.setObject(1, detail.getTotalPrice());
        stm.setObject(2, detail.getReloadId());


        return stm.executeUpdate() > 0;
    }
}
