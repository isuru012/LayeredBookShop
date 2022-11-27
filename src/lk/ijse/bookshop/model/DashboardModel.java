package lk.ijse.bookshop.model;

import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardModel {

    public static int getOrdersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusOrderId) FROM cusorder";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static int getProductsAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(ItemId) FROM item";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public static int getCustomersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusId) FROM customer";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    /*public static double getMondayAmount() {
        String sql="SELECT SUM(CusId) FROM customer";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }*/
}
