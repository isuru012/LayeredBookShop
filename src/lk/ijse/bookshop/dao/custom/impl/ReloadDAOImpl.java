package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CusReloadDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReloadDAOImpl {
    public boolean updateAmount(ArrayList<CusReloadDetailsDTO> cusReloadDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (CusReloadDetailsDTO detail : cusReloadDetailsDTOS) {
            if (!updateAmmount(detail)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateAmmount(CusReloadDetailsDTO detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().
                prepareStatement("UPDATE reload SET ReloadAmount=ReloadAmount-? WHERE ReloadId=?");
        stm.setObject(1, detail.getTotalPrice());
        stm.setObject(2, detail.getReloadId());


        return stm.executeUpdate() > 0;
    }
}
