package lk.ijse.bookshop.model;

import lk.ijse.bookshop.controller.AdminSupplierController;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.to.*;
import lk.ijse.bookshop.util.CrudUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
                        boolean updatePayment=updatePayment(supplierOrder.getSupplierId(),supplierOrder.getSupOrderId());
                        if(updatePayment) {
                            DBConnection.getDBConnection().getConnection().commit();
                            return true;
                        }
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }

    private static boolean updatePayment(String supplierId, String supOrderId) throws SQLException, ClassNotFoundException {
        String paymentId=generateNextPaymentId(PaymentModel.generateCurrentPaymentId());

        double amount=0;

        String sql="SELECT SUM(suporderdetails.TotalPrice) FROM suporderdetails WHERE SupOrderId=?";
        ResultSet resultSet=CrudUtil.execute(sql,supOrderId);

        if (resultSet.next()){
            amount=resultSet.getDouble(1);
        }

        Date date = Date.valueOf(LocalDate.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));

        String username=SupplierModel.getAdminUsername();

        Payment payment=new Payment(paymentId,amount,date,time,username,supOrderId,null);

        PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO payment VALUES(?,?,?,?,?,?,?) ");
        statement.setObject(1,payment.getPaymentId());
        statement.setObject(2,payment.getAmount());
        statement.setObject(3,payment.getDate());
        statement.setObject(4,payment.getTime());
        statement.setObject(5,payment.getUsername());
        statement.setObject(6,payment.getSupplierOrderId());
        statement.setObject(7,null);

        return statement.executeUpdate()>0;





    }

    private static String generateNextPaymentId(String generateCurrentPaymentId) {
        if (generateCurrentPaymentId != null) {
            String[] split = generateCurrentPaymentId.split("P");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "P000" + id;
            }else if(id>=100){
                return "P00" +id;
            }else if(id>=1000){
                return "P0"+id;
            }else if(id>=10000){
                return "P"+id;
            }else{

                return "P0000" + id;
            }
        }
        return "P00001";
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
