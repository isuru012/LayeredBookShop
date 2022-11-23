package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderModel {
    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusOrderId FROM cusorder ORDER BY CusOrderId DESC LIMIT 1";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Item searchDescription(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM item WHERE Description LIKE ?";
        ResultSet resultSet= CrudUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new Item(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                    resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getInt(6),resultSet.getString(7));
        }
        return null;
    }
}
