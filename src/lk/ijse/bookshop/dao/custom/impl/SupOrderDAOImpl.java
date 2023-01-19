package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupOrderDAOImpl {
    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT SupOrderId FROM suporder ORDER BY SupOrderId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
