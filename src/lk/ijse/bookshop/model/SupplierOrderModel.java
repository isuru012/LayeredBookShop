package lk.ijse.bookshop.model;

import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.to.Supplier;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderModel {

    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT SupOrderId FROM suporder ORDER BY SupOrderId DESC LIMIT 1";
        ResultSet resultSet= CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static ArrayList loadAllSupplierNames() throws SQLException, ClassNotFoundException {
        String sql="SELECT Name FROM supplier";
        ResultSet resultSet=CrudUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
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

    public static ArrayList getAllItemPrices(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(5));
        }
        return arrayList;
    }

    public static Supplier searchName(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM supplier WHERE Name LIKE ?";
        ResultSet resultSet= CrudUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new Supplier(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5) );
        }
        return null;
    }

    public static int getItemQuantity(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT QuantityOnHand FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getInt(1);

        }
        return -1;
    }

    public static double getSellingUnitPrice(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT SellingUnitPrice FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getInt(1);

        }
        return -1;
    }
}
