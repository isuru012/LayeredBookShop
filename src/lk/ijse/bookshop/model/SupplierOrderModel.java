package lk.ijse.bookshop.model;

import lk.ijse.bookshop.controller.AdminSupplierController;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.to.Supplier;
import lk.ijse.bookshop.to.SupplierOrder;
import lk.ijse.bookshop.to.SupplierOrderDetail;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.PreparedStatement;
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
        String sql="SELECT Description FROM item GROUP BY Description";
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

    public static String getUserName() throws SQLException, ClassNotFoundException {
        String sql="SELECT Username FROM user WHERE Role='Admin'";
        ResultSet resultSet=CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);

        }
        return null;
    }

    public static boolean placeOrder(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);
            PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO suporder VALUES(?,?,?,?,?) ");
            statement.setObject(1,supplierOrder.getSupOrderId());
            statement.setObject(2,supplierOrder.getDate());
            statement.setObject(3,supplierOrder.getTime());
            statement.setObject(4,supplierOrder.getSupplierId());
            statement.setObject(5,supplierOrder.getUserName());

            boolean isAddedOrder=statement.executeUpdate()>0;
            if (isAddedOrder){
                boolean saveOrderDetails=SupplierOrderDetailModel.saveOrderDetails(supplierOrder.getSupplierOrderDetails());
                if (saveOrderDetails){

                    boolean updateStock=checkItem(supplierOrder.getSupplierOrderDetails());
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

    public static boolean checkItem(ArrayList<SupplierOrderDetail> supplierOrderDetails) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetail detail : supplierOrderDetails) {
            if (!checkOneDetail(detail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkOneDetail(SupplierOrderDetail detail) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item WHERE ItemId=? AND BuyingUnitPrice=?";
        ResultSet resultSet=CrudUtil.execute(sql,detail.getItemId(),detail.getUnitPrice());
        PreparedStatement stm;
        if (resultSet.next()){
             stm = DBConnection.getDBConnection().getConnection().prepareStatement("UPDATE item SET QuantityOnHand=QuantityOnHand+? WHERE ItemId=? AND BuyingUnitPrice=?");
            stm.setObject(1, detail.getQuantity());
            stm.setObject(2, detail.getItemId());
            stm.setObject(3,detail.getUnitPrice());
        }
        else{
             stm = DBConnection.getDBConnection().getConnection().
                    prepareStatement("INSERT INTO item VALUES(?,?,?,?,?,?,?)");
            stm.setObject(1,detail.getItemId());
            stm.setObject(2,generateNextBatchNum(currentBatchNumber(detail)));
            stm.setObject(3,getDescription(detail.getItemId()));
            stm.setObject(4,detail.getUnitPrice());
            stm.setObject(5, AdminSupplierController.sellingUnitPrice);
            stm.setObject(6,detail.getQuantity());
            stm.setObject(7,null);
        }
        return stm.executeUpdate() > 0;
    }

    private static String getDescription(String itemId) throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,itemId);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    private static int generateNextBatchNum(int currentBatchNumber) {
        if (currentBatchNumber !=0) {
            return currentBatchNumber+1;
        }
        return -1;
    }

    private static int currentBatchNumber(SupplierOrderDetail detail) throws SQLException, ClassNotFoundException {
        String sql="SELECT BatchNumber FROM item WHERE ItemId=? ORDER BY BatchNumber  DESC LIMIT 1;";
        ResultSet resultSet=CrudUtil.execute(sql,detail.getItemId());
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    public static int getQtyTotalOfOneItem(String itemId) throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(QuantityOnHand) FROM item WHERE ItemId=?";
        ResultSet resultSet=CrudUtil.execute(sql,itemId);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
}
