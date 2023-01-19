package lk.ijse.bookshop.model;

import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CusOrderDTO;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.dao.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderModel {
    /*public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusOrderId FROM cusorder ORDER BY CusOrderId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }*/

    /*public static ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item GROUP BY Description";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }*/

    /*public static ItemDTO searchDescription(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text;
        String sql="SELECT * FROM item WHERE Description LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new ItemDTO(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                    resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getInt(6),resultSet.getString(7));
        }
        return null;
    }*/
    /*public static ArrayList getAllItemPrices(String ItemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,ItemCode);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(5));
        }
        return arrayList;
    }*/
    /*public static int getItemQuantity(String ItemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT QuantityOnHand FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,ItemCode);
        if (resultSet.next()){
            return resultSet.getInt(1);

        }
        return -1;
    }*/


    public static boolean placeOrder(CusOrderDTO cusOrderDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);
            PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT " +
                    "INTO cusorder VALUES(?,?,?,?,?) ");
            statement.setObject(1, cusOrderDTO.getCusOrderId());
            statement.setObject(2, cusOrderDTO.getDate());
            statement.setObject(3, cusOrderDTO.getTime());
            statement.setObject(4, cusOrderDTO.getCusId());
            statement.setObject(5, cusOrderDTO.getEmployeeId());

            boolean isAddedOrder=statement.executeUpdate()>0;
            if (isAddedOrder){
                boolean saveOrderDetails=OrderDetailModel.saveOrderDetails(cusOrderDTO.getCustomerOrderDetails());
                if (saveOrderDetails){
                    boolean updateStock=ItemModel.updateStock(cusOrderDTO.getCustomerOrderDetails());
                    if (updateStock){
                        DBConnection.getDBConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    /*public static int getQtyTotalOfOneItem(String itemId, double unitPrice) throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(QuantityOnHand) FROM item WHERE ItemId=? AND SellingUnitPrice=?";
        ResultSet resultSet= SQLUtil.execute(sql,itemId,unitPrice);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }*/
}
