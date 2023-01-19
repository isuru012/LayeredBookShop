package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.ReloadDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CusReloadDetailsDTO;
import lk.ijse.bookshop.dto.ReloadDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReloadDAOImpl implements ReloadDAO {

    public  ReloadDTO searchDescription(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM reload WHERE ServiceProvider LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new ReloadDTO(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getDouble(3),resultSet.getDouble(4));

        }
        return null;
    }
    public  ArrayList loadAllServiceProviders() throws SQLException, ClassNotFoundException {
        String sql="SELECT ServiceProvider FROM reload";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
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
