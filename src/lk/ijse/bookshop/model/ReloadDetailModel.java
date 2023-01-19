package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CusReloadDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReloadDetailModel {
    /*public static boolean saveReloadDetails(ArrayList<CusReloadDetailsDTO> cusReloadDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (CusReloadDetailsDTO ord : cusReloadDetailsDTOS) {
            if (!addReloadDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private static boolean addReloadDetail(CusReloadDetailsDTO ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusreloaddetails values(?,?,?)");

        statement.setObject(1, ord.getCusReloadId());
        statement.setObject(2, ord.getReloadId());
        statement.setObject(3, ord.getTotalPrice());


        return statement.executeUpdate() > 0;
    }*/
}
