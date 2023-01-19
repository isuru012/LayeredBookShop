package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CusReloadDAOImpl {
    public  String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusReloadId FROM cusreload ORDER BY CusReloadId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
