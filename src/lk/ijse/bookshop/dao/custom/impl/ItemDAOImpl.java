package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.controller.AdminSupplierOrderController;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.ItemDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.Item;
import lk.ijse.bookshop.entity.SupOrderDetails;
import lk.ijse.bookshop.view.tm.AdminItemTm;
import lk.ijse.bookshop.view.tm.CashierItemTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    public int getQtyTotalOfOneItem(String itemId) throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(QuantityOnHand) FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,itemId);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public  String getDescription(String itemId) throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,itemId);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
    public  boolean deleteOffer(String offerId) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET OfferId=?  WHERE ItemId=?";
        return SQLUtil.execute(sql,null,offerId);
    }
    public  boolean updateItemData(String itemCode, String code, int batchNumber) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET OfferId=? WHERE ItemId=? AND BatchNumber=?";
        return SQLUtil.execute(sql,code,itemCode,batchNumber);

    }
    public  int getProductsAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(ItemId) FROM item";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public  String getOfferId(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT OfferId FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
    private  int generateNextBatchNum(int currentBatchNumber) {
        if (currentBatchNumber !=0) {
            return currentBatchNumber+1;
        }
        return -1;
    }

    private int currentBatchNumber(SupOrderDetails detail) throws SQLException, ClassNotFoundException {
        String sql="SELECT BatchNumber FROM item WHERE ItemId=? ORDER BY BatchNumber  DESC LIMIT 1;";
        ResultSet resultSet= SQLUtil.execute(sql,detail.getItemId());
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
    public boolean checkItem(ArrayList<SupOrderDetails> supplierOrderDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (SupOrderDetails detail : supplierOrderDetailsDTOS) {
            if (!checkOneDetail(detail)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkOneDetail(SupOrderDetails detail) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item WHERE ItemId=? AND BuyingUnitPrice=?";
        ResultSet resultSet= SQLUtil.execute(sql,detail.getItemId(),detail.getUnitPrice());
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
            stm.setObject(5, AdminSupplierOrderController.sellingUnitPrice);
            stm.setObject(6,detail.getQuantity());
            stm.setObject(7,null);
        }
        return stm.executeUpdate() > 0;
    }
    public ArrayList loadAllDescriptionIds() throws SQLException, ClassNotFoundException {
        String sql="SELECT Description FROM item GROUP BY Description";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
    public  ArrayList loadAllItemPrices(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT SellingUnitPrice FROM item WHERE Description=?";
        ResultSet resultSet= SQLUtil.execute(sql,text);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
    public  int getBatchNumber(double text, String itemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT BatchNumber FROM item WHERE ItemId=? AND SellingUnitPrice=?";
        ResultSet resultSet= SQLUtil.execute(sql,itemCode,text);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public String getItemId(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT ItemId FROM item WHERE Description=?";
        ResultSet resultSet= SQLUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
    public Item search(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text;
        String sql="SELECT * FROM item WHERE Description LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new Item(resultSet.getString(1),resultSet.getInt(2),resultSet.getString(3),
                    resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getInt(6),resultSet.getString(7));
        }
        return null;
    }
    public ArrayList getAllItemPrices(String ItemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,ItemCode);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(5));
        }
        return arrayList;
    }
    public int getItemQuantity(String ItemCode) throws SQLException, ClassNotFoundException {
        String sql="SELECT QuantityOnHand FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,ItemCode);
        if (resultSet.next()){
            return resultSet.getInt(1);

        }
        return -1;
    }
    public double getSellingUnitPrice(String text) throws SQLException, ClassNotFoundException {
        String sql="SELECT SellingUnitPrice FROM item WHERE ItemId=?";
        ResultSet resultSet= SQLUtil.execute(sql,text);
        if (resultSet.next()){
            return resultSet.getInt(1);

        }

        return -1;
    }

    public int getQtyTotalOfOneItem(String itemId, double unitPrice) throws SQLException, ClassNotFoundException {
        String sql="SELECT SUM(QuantityOnHand) FROM item WHERE ItemId=? AND SellingUnitPrice=?";
        ResultSet resultSet= SQLUtil.execute(sql,itemId,unitPrice);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public boolean updateStock(ArrayList<CusOrderDetails> orderDetailDTOS)
            throws SQLException, ClassNotFoundException {
        for (CusOrderDetails detail : orderDetailDTOS) {
            if (!updateItem(detail)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateItem(CusOrderDetails detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE item SET QuantityOnHand=QuantityOnHand-? WHERE ItemId=? " +
                        "AND SellingUnitPrice=?");
        stm.setObject(1, detail.getQuantity());
        stm.setObject(2, detail.getItemId());
        stm.setObject(3, detail.getUnitPrice());


        return stm.executeUpdate() > 0;
    }

    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            if (resultSet.getString(7) == null) {
                arrayList.add(new CashierItemTm(resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getDouble(5),
                        resultSet.getInt(6),
                        0));
            } else {
                String sql2 = "select Amount from offer where OfferId=? ";
                ResultSet resultSet1 = SQLUtil.execute(sql2, resultSet.getString(7));
                if (resultSet1.next()) {
                    arrayList.add(new CashierItemTm(resultSet.getString(1),
                            resultSet.getInt(2),
                            resultSet.getString(3), resultSet.getDouble(5),
                            resultSet.getInt(6),
                            resultSet1.getDouble(1)));
                }
            }

        }
        return arrayList;
    }

    public boolean updateSupplierStock(ArrayList<SupOrderDetails> supplierOrderDetailsDTOS)
            throws SQLException, ClassNotFoundException {
        for (SupOrderDetails detail : supplierOrderDetailsDTOS) {
            if (!updateSupplerItem(detail)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateSupplerItem(SupOrderDetails detail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection()
                .prepareStatement("UPDATE item SET QuantityOnHand=QuantityOnHand+? WHERE ItemId=?");
        stm.setObject(1, detail.getQuantity());
        stm.setObject(2, detail.getItemId());

        return stm.executeUpdate() > 0;
    }

    public ArrayList getAllDetailsForAdminItem() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            if (resultSet.getString(7) == null) {
                arrayList.add(new AdminItemTm(resultSet.getString(1), resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4), resultSet.getDouble(5),
                        resultSet.getInt(6), 0));
            } else {
                String sql2 = "select Amount from offer where OfferId=? ";
                ResultSet resultSet1 = SQLUtil.execute(sql2, resultSet.getString(7));
                if (resultSet1.next()) {
                    arrayList.add(new AdminItemTm(resultSet.getString(1), resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4), resultSet.getDouble(5),
                            resultSet.getInt(6), resultSet1.getDouble(1)));
                }
            }

        }
        return arrayList;
    }


    public boolean insert(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?,?)";
        return SQLUtil.execute(sql, item.getItemId(), item.getBatchNumber(), item.getDescription(),
                item.getBuyingUnitPrice(), item.getSellingUnitPrice(),
                item.getQuantityOnHand(), item.getOfferId());
    }

    public String getId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ItemId FROM item ORDER BY ItemId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE ItemId=? ";
        return SQLUtil.execute(sql, itemId);
    }


    /*public boolean update(String itemCode, int batchNumber, String desCriptionText, double buyingPrice, double
    sellingPrice, int quantity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET Description=?,BuyingUnitPrice=?,SellingUnitPrice=?,QuantityOnHand=? WHERE
        ItemId=? AND  BatchNumber=?";
        return SQLUtil.execute(sql, desCriptionText, buyingPrice, sellingPrice, quantity, itemCode, batchNumber);
    }*/
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET Description=?,BuyingUnitPrice=?,SellingUnitPrice=?,QuantityOnHand=? WHERE " +
                "ItemId=? AND  BatchNumber=?";
        return SQLUtil.execute(sql, item.getDescription(), item.getBuyingUnitPrice(), item.getSellingUnitPrice(),
                item.getQuantityOnHand(), item.getItemId(), item.getBatchNumber());
    }
}
