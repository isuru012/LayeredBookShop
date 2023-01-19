package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CusOrderDAOImpl {
    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusOrderId FROM cusorder ORDER BY CusOrderId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
    public static int getOrdersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusOrderId) FROM cusorder";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
}
